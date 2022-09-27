package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
