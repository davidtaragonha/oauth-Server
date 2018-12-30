package com.alonso.spring.oauth2.view;

import com.alonso.spring.oauth2.model.User;
import org.springframework.stereotype.Component;


//TODO user mapstruct and ignore field password
@Component
public class UserViewMapper {
    public UserView fromDomain(User user){
        UserView userView = new UserView();
        userView.setAccountExpired(user.isAccountExpired());
        userView.setAccountLocked(user.isAccountLocked());
        userView.setAuthorities(user.getAuthorities());
        userView.setCredentialsExpired(user.isCredentialsExpired());
        userView.setEnabled(user.isEnabled());
        userView.setId(user.getId());
        userView.setPassword("******");
        userView.setUsername(user.getUsername());
        return userView;
    }
}
