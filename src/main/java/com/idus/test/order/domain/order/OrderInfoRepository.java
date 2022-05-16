package com.idus.test.order.domain.order;

import com.idus.test.order.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long> {
    Optional<OrderInfoEntity> findByOrderNum(String randomCode);

    List<OrderInfoEntity> findAllByUserOrderBySeqDesc(UserEntity user);
}
