package com.codesoom.assignment.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private String password;

    private boolean deleted = false;

    protected User() {
    }

    @Builder
    public User(Long id, String email, String name, String password, boolean deleted) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.deleted = deleted;
    }

    public void changeWith(User source) {
        name = source.name;
        password = source.password;
    }

    public void inActivate() {
        deleted = true;
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return !deleted && passwordEncoder.matches(password, this.password);
    }

    public void changePassword(String newPassword, PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(newPassword);
    }
}
