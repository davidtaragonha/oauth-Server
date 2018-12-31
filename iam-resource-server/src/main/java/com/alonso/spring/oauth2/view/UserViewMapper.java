package com.alonso.spring.oauth2.view;

import com.alonso.spring.oauth2.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserViewMapper {

    @Mapping(target = "password", constant = "******" )
    UserView userToUserView(User user);
}
