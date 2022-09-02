package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.User;
import com.stsetsevich.smartpoker.domain.UserSmarthandAccountAndCookies;
import org.springframework.data.repository.CrudRepository;

public interface UserSmarthandAccountAndCookiesRepo extends CrudRepository<UserSmarthandAccountAndCookies, Long> {
    UserSmarthandAccountAndCookies findByUserId(int id);
    UserSmarthandAccountAndCookies findByUser(User user);
}
