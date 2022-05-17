package com.idus.test.order.domain.user;

import com.idus.test.order.controller.dto.UserDto;
import com.idus.test.order.domain.order.OrderInfoEntity;
import com.idus.test.order.domain.order.QOrderInfoEntity;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    JPAQueryFactory queryFactory;

    @Override
    public UserDto.UsersOrderPager getUserList(UserDto.UserSearchRequest request) {
        List<UserDto.UserOrder> usersOrder = findUserOrders(request);
        long countUserOrder = countUserOrders();
        return UserDto.UsersOrderPager.builder()
                .usersOrder(usersOrder)
                .viewCount(request.getViewCount())
                .currentPage(request.getPage())
                .maxPage((int) Math.ceil(countUserOrder / (double) request.getViewCount()))
                .build();
    }

    private List<UserDto.UserOrder> findUserOrders(UserDto.UserSearchRequest request) {
        List<Tuple> result = queryFactory.select(QUserEntity.userEntity, QOrderInfoEntity.orderInfoEntity)
                .from(QUserEntity.userEntity)
                    .leftJoin(QOrderInfoEntity.orderInfoEntity)
                    .on(QUserEntity.userEntity.eq(QOrderInfoEntity.orderInfoEntity.user))
                .where(QOrderInfoEntity.orderInfoEntity.seq.in(lastOrderSeqs()).or(QOrderInfoEntity.orderInfoEntity.isNull()),
                        likeEmailOrName(request.getSearch()))
                .offset(request.getViewCount() * (request.getPage() - 1))
                .limit(request.getViewCount())
                .orderBy(QUserEntity.userEntity.seq.desc())
                .fetch();
        return result.stream()
                .map(tuple -> {
                    UserEntity userEntity = tuple.get(QUserEntity.userEntity);
                    OrderInfoEntity orderEntity = tuple.get(QOrderInfoEntity.orderInfoEntity);
                    return UserDto.UserOrder.of(userEntity, Optional.ofNullable(orderEntity));
                })
                .collect(Collectors.toList());
    }


    private List<Long> lastOrderSeqs() {
        return queryFactory.select(QOrderInfoEntity.orderInfoEntity.seq.max())
                .from(QOrderInfoEntity.orderInfoEntity)
                .groupBy(QOrderInfoEntity.orderInfoEntity.user)
                .fetch();
    }

    private Long countUserOrders() {
        return queryFactory.select(QUserEntity.userEntity.count())
                .from(QUserEntity.userEntity)
                .fetchOne();
    }

    private BooleanExpression likeEmailOrName(String search) {
        if (search == null) {
            return null;
        }
        return QUserEntity.userEntity.email.contains(search)
                .or(QUserEntity.userEntity.userName.contains(search));
    }
}
