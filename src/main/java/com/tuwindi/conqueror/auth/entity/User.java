package com.tuwindi.conqueror.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuwindi.conqueror.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "username", unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "is_admin")
    private boolean admin;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    private transient String token;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles = new HashSet<>();
}

