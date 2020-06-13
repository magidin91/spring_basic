package com.education.jwt;

import com.education.security.SecurityUserDetailsManager;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static org.springframework.util.StringUtils.hasText;

@Service
public class TokenAuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationService.class);
    private final static String SECRET = "Secret"; //ключ шифрования
    private final static long EXPIRATION_DATE = 864_000_000; // 10 ДНЕЙ, время окончания токена
    private final static String TOKEN_PREFIX = "Bearer";
    private final static String HEADER_STRING = "Authorization"; //ключ заголовка, его value - токен

    private static SecurityUserDetailsManager userDetailsManager;

    public TokenAuthenticationService(SecurityUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    /**
     * Добавляет в ответ токен при успешной авторизации
     */
    static void addAuthentication(HttpServletResponse response, String username) {
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + generateToken(username));
    }

    /**
     * Генерирует Authentication токен
     */
    private static String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS512, SECRET) //шифруем токен
                .compact();
    }

    /**
     * Вызывается при повторном запросе, когда в запросе уже есть токен
     */
    static Authentication getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        if (!hasText(token)) {
            return null;
        }
        /* можно сделать проверку наличия сессии по токену */
        String userName = getUsername(token);
        UserDetails user = userDetailsManager.loadUserByUsername(userName); //нашли юзера из БД (хранлища)
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities() //указываем юзера и его права
        );
        return authentication;
    }

    /**
     * Получаем токен без префикса из входящего запроса
     */
    private static String getToken(HttpServletRequest request) {
        if (request.getHeader(HEADER_STRING) != null) {
            return request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX + " ", "");
            // по ключу HEADER_STRING получаем, токен, убираем префикс
        } else {
            return null;
        }
    }

    private static String getUsername(String token) {
        try {
            return token != null ? Jwts.parser()// здесь также можно проверить не просрочен ли токен
                    .setSigningKey(SECRET) //дешифруем токен
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject() : null; //получаем юзернейм из subject
        } catch (JwtException e) {
            LOGGER.info("Ошибка обработки токена: {}", token);
            return null;
        }
    }
}
