package com.intelliarts.test.service.impl;

import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.repository.SoldItemsRepository;
import com.intelliarts.test.service.CategoryService;
import com.intelliarts.test.service.SoldItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class SoldItemsServiceImpl implements SoldItemsService {

    private final SoldItemsRepository soldItemsRepository;

    @Autowired
    public SoldItemsServiceImpl(SoldItemsRepository soldItemsRepository) {
        this.soldItemsRepository = soldItemsRepository;
    }

    public String totalForMonth(YearMonth date) {
        List<SoldItems> all = soldItemsRepository.findAll();
        List<SoldItems> list = all.stream().filter(
                        one -> one.getDate().getYear() == date.getYear() &&
                                one.getDate().getMonth() == date.getMonth())
                .collect(toList());
        double sum = 0;
        for (SoldItems one : list) {
            sum += one.getPrice();
        }
        return "Total for the  " + date + " is " + sum;
    }

    @Override
    public List<SoldItemsDTO> reportSinceDate(LocalDate date) {
        List<SoldItemsDTO> list = soldItemsRepository.allSoldSinceDate(date);
        if (list.isEmpty()) {
            throw new NullPointerException("there were not sold items from date " + date);
        }
//        if we need to output total, we can return total
//        double total = 0;
//        for (SoldItemsDTO one : list) {
//            total += one.getPrice();
//        }
        return list.stream().sorted().collect(toList());

    }


    @Override
    public void create(SoldItems soldItems) {
        soldItemsRepository.save(soldItems);
    }


}
