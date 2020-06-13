package com.education.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {
    /**
     * Фильтр вызывается при запросе до вызова метода контроллера
     * С помощью метода hasPermission из нашего DefaultPermissionEvaluator, doFilter проверяет,
     * есть ли у юзера необходимое право
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpServletRequest);
        /* указываем полученную аутентификацию */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /* на основе прав пользователя из объекта authentication будет вызываться фильтр для запросов и ответов */
        filterChain.doFilter(request, response);
    }
}
