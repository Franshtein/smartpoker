package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PlayerRepo extends CrudRepository<Player, Long>{


    Player findByNickname(String nickname);
    @Query("select p.totalHands from Player p where p.nickname = ?1")
    double findTotalHands(String nickname);

   // @Query("select p from Player p where p.nickname = ?1")
    @Query(
            value = "select total_hands, ?1 from player where nickname = 'Franshtik (PS)'",
            nativeQuery = true)
    ArrayList<String> findSomething(String stat);

}
