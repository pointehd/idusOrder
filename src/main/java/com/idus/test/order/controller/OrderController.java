package com.idus.test.order.controller;

import com.idus.test.order.controller.dto.OrderDto;
import com.idus.test.order.domain.order.OrderService;
import com.idus.test.order.utils.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto.OrderResponse> createOrder(@Valid @RequestBody OrderDto.CreateOrder productRequest) {
        String userName = (String) SecurityUtil.getCurrentMemberId().getPrincipal();
        return ResponseEntity.ok(orderService.createOrder(userName, productRequest.getProduct()));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto.OrderResponse>> getOrders() {
        String userName = (String) SecurityUtil.getCurrentMemberId().getPrincipal();
        return ResponseEntity.ok(orderService.getOrders(userName));
    }
}
