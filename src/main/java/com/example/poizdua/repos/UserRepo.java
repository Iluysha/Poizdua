package com.example.poizdua.repos;

import com.example.poizdua.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    //User findByEmailOrUsername(String email, String username);
    User findByActivationCode(String code);
}