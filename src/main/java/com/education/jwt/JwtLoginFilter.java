package com.education.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Используется при попытке залогиниться
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    /* AuthenticationManager находится в контексте спринга */
    public JwtLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    /**
     * Вызывается при любом запросе пользователя.
     * Попытка перичной авторизации:
     * получаем объект AccountCredentials из json запроса,
     * далее на его основе создаем new UsernamePasswordAuthenticationToken,
     * на его основе получаем Authentication.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        /* получаем данные из запроса (username, password) и маппим их к AccountCredentials */
        AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);

        return getAuthenticationManager().authenticate( // получаем AuthenticationManager из контекста спринга и пытаемся аутентифицировать
                /* Происходит обращение к SecurityUserDetailsManager, который вычитывает пользователя (UserDetails: логин, пароль, разрешения)
                * из хранилища, сравниваем этого пользователя с AccountCredentials, и если они совпадают,
                *  вызваем метод successfulAuthentication */

                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList() //права пользователя
                ));
        /* UsernamePasswordAuthenticationToken реализует Authentication, который представляет токен дял аутентификации запроса
         * Однажды аутентифицированный запрос обычно сохраняется в thread-local SecurityContext управляемый  SecurityContextHolder */
    }


    /**
     * Вызывается при успешной аутентификации, добавляет в риспонс токен обратно на фронт.
     */
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, auth.getName());
    }
}
