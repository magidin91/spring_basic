package com.education.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Класс для создания объекта пользователя с именем, паролем и разрешениями. User реализует UserDetails.
 * Также есть второй конструктор с флагами: boolean enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
 */
public class SecurityUser extends User {
    /* GrantedAuthority - разрешение на доступ к чему-то */
    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
