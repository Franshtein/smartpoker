package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.User;
import com.stsetsevich.smartpoker.domain.SmarthandCookies;
import org.springframework.data.repository.CrudRepository;

public interface SmarthandCookiesRepo extends CrudRepository<SmarthandCookies, Long> {

    SmarthandCookies findById(long id);
   // SmarthandCookies findByUser(User user);
}
