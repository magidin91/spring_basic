package com.education.config;

import com.education.jwt.JwtAuthenticationFilter;
import com.education.jwt.JwtLoginFilter;
import com.education.security.SecurityUserDetailsManager;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //провевка перд и после вызова метода контроллера
@Import(SecurityBeanConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecurityUserDetailsManager userDetailsManager;

    public SecurityConfig(SecurityUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    /**
     * Метод конфигурации
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests() //тут к примеру должна возвращаться страница авторизации
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll() //логин тоже без авторизации
                .anyRequest().authenticated() //все остальные запросы должны иметь авторизацию
                .and()
                /* перед обращением к "/login" добавляем обработчик JwtLoginFilter и результат его преобразуем к
                UsernamePasswordAuthenticationFilter.class */
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                /* при любом запросе будет вызываться фильтр для проверки прав юзера */
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsManager);

    }
}
