package com.intelliarts.test.service;


import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface SoldItemsService {


    String totalForMonth(YearMonth date);

    List<SoldItemsDTO> reportSinceDate(LocalDate localDate);

    void create(SoldItems soldItems);

}
