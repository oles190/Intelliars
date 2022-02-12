package com.intelliarts.test.repository;

import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SoldItemsRepository extends JpaRepository<SoldItems, Long> {

    @Query(value = "select new  com.intelliarts.test.dto.SoldItemsDTO(name, sum(price), sum (items))" +
            "from SoldItems where date  between ?1 and current_date group by name ")
    List<SoldItemsDTO> allSoldSinceDate(LocalDate localDate);


}

