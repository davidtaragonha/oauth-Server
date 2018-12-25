package com.alonso.spring.oauth2.repository;

import com.alonso.spring.oauth2.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByModule(String module);
}
