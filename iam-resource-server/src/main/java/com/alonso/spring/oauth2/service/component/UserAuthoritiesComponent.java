package com.alonso.spring.oauth2.service.component;

import com.alonso.spring.oauth2.model.Authority;
import com.alonso.spring.oauth2.model.User;
import com.alonso.spring.oauth2.repository.AuthorityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

//TODO This class is not a service, we use like a component for the User Service, Review like manage this 'components'
@Component
public class UserAuthoritiesComponent {
    private final AuthorityRepository authorityRepository;

    public UserAuthoritiesComponent(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public User assignAuthority(User user, long authorityId) {
        Authority authorityToAssign = authorityRepository.findById(authorityId).orElseThrow(NoSuchElementException::new);
        user.getAuthorities().add(authorityToAssign);

        return user;
    }

    public User revokeAuthority(User user, long authorityId) {
        List<Authority> authoritiesFiltered = user.getAuthorities()
                                            .stream()
                                            .filter(authority -> !authority.getId().equals(authorityId))
                                            .collect(toList());
        user.setAuthorities(authoritiesFiltered);

        return user;
    }
}
