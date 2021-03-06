package com.tuwindi.conqueror.auth.service;

import com.tuwindi.conqueror.auth.entity.User;
import com.tuwindi.conqueror.auth.exception.CustomException;
import com.tuwindi.conqueror.auth.repository.UserRepository;
import com.tuwindi.conqueror.base.response.CResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CResponse<List<User>> listOfUsers() {
        try {
            List<User> users = userRepository.findAll();
            return CResponse.success(users, users.size() + " utilisateur.s trouve.s");
        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur est servenue!");
        }
    }

    public CResponse<User> create(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User utilisateur = userRepository.save(user);
            return CResponse.success(utilisateur, "Utilisateur ajoutee avec succes");
        } else {
            throw new CustomException("Ce nom d'utilisateur est déjà utilisé", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public CResponse<User> edit(User user) {
        try {
            // if empty so => not exist, else => exist
            boolean existingUser = userRepository.checkExistingUser(user.getId(), user.getUsername()).isEmpty();
            if (!existingUser) {
                return CResponse.error("Ce nom d'utilisateur existe deja");
            }
            User oldUser = userRepository.findUserById(user.getId());
            if (oldUser == null) {
                return CResponse.error("Cet utilisateur n'existe pas");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                user.setPassword(oldUser.getPassword());
            }
            userRepository.save(user);
            return CResponse.success(user, "Utilisateur modifie avec succes!");

        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur est servenue!");
        }
    }

    public CResponse<User> remove(Long id) {
        try {
            if (userRepository.findById(id).isPresent()) {
                userRepository.deleteById(id);
            }
            return CResponse.validate("Suppression avec succes");

        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur est servenue");
        }
    }

    public CResponse<User> getUser(Long id) {
        try {
            return CResponse.success(userRepository.findUserById(id), "Recuperer avec succes");
        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Ce nom existe deja!");
        }
    }
}

