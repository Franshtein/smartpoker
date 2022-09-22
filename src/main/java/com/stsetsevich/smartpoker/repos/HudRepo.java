package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.Hud;
import com.stsetsevich.smartpoker.domain.RoundOfBidding;
import org.springframework.data.repository.CrudRepository;

public interface HudRepo extends CrudRepository<Hud, Long> {
    Hud findByUserId(Long id);
    Hud findByUserIdAndRoundOfBidding(Long id, RoundOfBidding roundOfBidding);
}
