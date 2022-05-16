package com.idus.test.order.domain.order;

import com.idus.test.order.controller.dto.OrderDto;
import com.idus.test.order.domain.user.UserEntity;
import com.idus.test.order.domain.user.UserService;
import com.idus.test.order.utils.RandomUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    OrderInfoRepository orderRepository;
    UserService userService;

    @Transactional
    public OrderDto.OrderResponse createOrder(String userName, String product) {
        String oderId = createOrderId();
        UserEntity user = getUserEntity(userName);
        OrderInfoEntity orderInfoEntity =
                orderRepository.save(OrderInfoEntity.of(user, oderId, product));
        return OrderDto.OrderResponse.from(orderInfoEntity);
    }

    private UserEntity getUserEntity(String userName) {
        return userService.getOptionalUserByUserName(userName)
                .orElseThrow(()-> new IllegalArgumentException("회원 정보가 없습니다."));
    }


    private String createOrderId() {
        String randomCode = RandomUtil.randomCode(12);
        int count = 0;
        int max = 50;
        while (orderRepository.findByOrderNum(randomCode).isPresent()) {
            randomCode = RandomUtil.randomCode(12);
            count++;
            if (count > max) {
                randomCode = RandomUtil.randomCode(11);
            }
            log.info("random Code: {}, count{}", randomCode, count);
        }
        return randomCode;
    }

    public List<OrderDto.OrderResponse> getOrders(String userName) {
        return orderRepository.findAllByUserOrderBySeqDesc(getUserEntity(userName))
                .stream()
                .map(OrderDto.OrderResponse::from)
                .collect(Collectors.toList());
    }
}
