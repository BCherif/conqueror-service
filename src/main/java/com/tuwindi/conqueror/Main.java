package com.tuwindi.conqueror;

import com.tuwindi.conqueror.auth.entity.User;
import com.tuwindi.conqueror.auth.repository.UserRepository;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final ConfigurableListableBeanFactory configurableListableBeanFactory;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Main(ConfigurableListableBeanFactory configurableListableBeanFactory,
                PasswordEncoder passwordEncoder,
                UserRepository userRepository) {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("admin") != null) return;
        User user = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .firstName("Baba")
                .lastName("Maiga")
                .fullName("Baba Soumaila Maiga")
                .phone("73403651")
                .email("bsmaiga@tuwindi.org")
                .address("Banankabougou")
                .active(true)
                .admin(true)
                .build();
        userRepository.save(user);
    }
}
