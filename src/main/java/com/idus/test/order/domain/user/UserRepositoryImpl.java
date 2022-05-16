package com.idus.test.order.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    JPAQueryFactory queryFactory;

    @Override
    public List<UserEntity> getUserList() {
        return queryFactory.select(QUserEntity.userEntity)
                .from(QUserEntity.userEntity)
                .fetch();
    }
}
