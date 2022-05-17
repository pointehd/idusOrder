package com.idus.test.order.config.security;

import com.idus.test.order.domain.token.TokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    JwtTokenProvider provider;
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && provider.validateToken(jwt)) {
                String userId = provider.getUserIdFromJWT(jwt);

                UserAuthentication authentication = new UserAuthentication(userId, null, null); //id를 인증한다.
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                if (!StringUtils.hasText(jwt)) {
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }

                if (provider.validateToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료.");
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && StringUtils.hasText(token) && !tokenService.isExpiredtoken(token)) {
            return token;
        }
        return null;
    }
}