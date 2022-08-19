package com.stsetsevich.smartpoker.repos;

import com.stsetsevich.smartpoker.domain.Player;
import com.stsetsevich.smartpoker.domain.Stat;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StatRepo extends CrudRepository<Stat, Long> {
    Stat findStatByStatname(String statname);
    String findStatByStatnameOrderById(String statname);
    ArrayList<Stat> findAllByStatnameIsNotNullOrderById();
}
