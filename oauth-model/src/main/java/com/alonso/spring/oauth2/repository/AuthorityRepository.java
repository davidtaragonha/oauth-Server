package com.alonso.spring.oauth2.repository;

import com.alonso.spring.oauth2.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByModule(String module);

    @Query(value = "SELECT DISTINCT authority.ID, authority.MODULE, authority.NAME FROM AUTHORITY authority " +
            "LEFT OUTER JOIN USERS_AUTHORITIES AS usersAuthorities " +
            "ON authority.ID = usersAuthorities.AUTHORITY_ID AND usersAuthorities.USER_ID <> :userId " +
            "WHERE authority.MODULE = :moduleId ",
    nativeQuery = true)
    List<Authority> findAuthoritiesByModuleNotAssignedToUser(@Param("moduleId") String module, @Param("userId") long userId);

    @Query(value = "SELECT DISTINCT authority.ID, authority.MODULE, authority.NAME FROM AUTHORITY authority " +
            "LEFT OUTER JOIN USERS_AUTHORITIES AS usersAuthorities " +
            "ON authority.ID = usersAuthorities.AUTHORITY_ID AND usersAuthorities.USER_ID <> :userId ",
    nativeQuery = true)
    List<Authority> findAuthoritiesNotAssignedToUser(@Param("userId") long userId);
}