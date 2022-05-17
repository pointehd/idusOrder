package com.idus.test.order.domain.token;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class TokenService {
    ExpiredTokenRepository tokenRepository;

    public boolean isExpiredtoken(String token) {
        return tokenRepository.findByToken(token).isPresent();
    }


    public boolean expireToken(String token) {
        if (!isExpiredtoken(token)) {
            tokenRepository.save(ExpiredTokenEntity.from(token));
        }
        return true;
    }
}
