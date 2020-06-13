package com.education.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Класс дял проверки привелегий
 */
@Component
public class DefaultPermissionEvaluator implements PermissionEvaluator {
    /**
     * Если право SecurityPermission - product.read, то product - resource, read - action.
     * Возвращает тру, если он юзера в списке прав, есть неоходимое право, и перейдет к методу контроллера.
     * Или вернут false? и сразу вернет response 403
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object resource, Object action) {
        User user = (User) authentication.getPrincipal();
        SecurityPermission permission = new SecurityPermission(resource + "." + action);
        return user.getAuthorities().contains(permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
