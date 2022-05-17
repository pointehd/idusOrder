package com.idus.test.order.domain.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpiredTokenRepository extends JpaRepository<ExpiredTokenEntity, Long> {
    Optional<ExpiredTokenEntity> findByToken(String token);
}
