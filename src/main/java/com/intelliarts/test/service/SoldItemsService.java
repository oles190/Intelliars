package com.intelliarts.test.service;


import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;

import java.time.LocalDate;
import java.util.List;

public interface SoldItemsService {

    List<SoldItems> totalForDay(LocalDate localDate);


    String totalForMonth(LocalDate date);

    List<SoldItemsDTO> reportSinceDate(LocalDate localDate);


}
