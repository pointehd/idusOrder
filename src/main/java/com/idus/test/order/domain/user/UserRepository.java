package com.idus.test.order.domain.user;

import com.idus.test.order.controller.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserDto.UserInfo> findByUserName(String id);
}
