package com.idus.test.order.domain.user;

import com.idus.test.order.controller.dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    public UserDto.UsersOrderPager getUserList(UserDto.UserSearchRequest request);
}
