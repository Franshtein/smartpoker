package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepo extends CrudRepository<Player, Long> {
    Player findByNickname(String nickname);
}
