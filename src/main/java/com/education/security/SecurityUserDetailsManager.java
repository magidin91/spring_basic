package com.education.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityUserDetailsManager implements UserDetailsManager {
    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    /**
     * Здесь должна быть реализована логика по вычитке пользователя из хранилища данных
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (!userName.equals("user")) {
            return null;
        }
        return new SecurityUser("user", "{noop}123", List.of(
                new SecurityPermission("product.read"),
                new SecurityPermission("product.readById"),
                new SecurityPermission("product.create"),
                new SecurityPermission("product.update"),
                new SecurityPermission("product.delete"),
                new SecurityPermission("type.read"),
                new SecurityPermission("type.readById"),
                new SecurityPermission("type.create"),
                new SecurityPermission("type.update"),
                new SecurityPermission("type.delete")
                ));
    }
}
