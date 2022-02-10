package com.intelliarts.test.service.impl;

import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.repository.SoldItemsRepository;
import com.intelliarts.test.service.SoldItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SoldItemsServiceImpl implements SoldItemsService {

    private final SoldItemsRepository soldItemsRepository;


    @Autowired
    public SoldItemsServiceImpl(SoldItemsRepository soldItemsRepository) {
        this.soldItemsRepository = soldItemsRepository;
    }

    //TODO :::::::::::::::
    @Override
    public List<SoldItems> totalForDay(LocalDate localDate) {
        List<SoldItems> list = soldItemsRepository.getSoldItemsByDate(localDate)
                .stream().sorted().collect(Collectors.toList());
        double sum = 0;
        for (SoldItems one : list) {
            sum += one.getPrice();
        }
        return list;

    }


    //TODO :::::::::::::::
    public String totalForMonth(LocalDate date) {
        List<SoldItems> all = soldItemsRepository.findAll();
        List<SoldItems> list = all.stream().filter(
                        one -> one.getDate().getYear() == date.getYear() &&
                                one.getDate().getMonth() == date.getMonth())
                .collect(Collectors.toList());
        double sum = 0;
        for (SoldItems one : list) {
            sum += one.getPrice();
        }
        return "Total for the  " + date.getMonth() + " " + date.getYear() + " is " + sum;
    }

    @Override
    public List<SoldItemsDTO> reportSinceDate(LocalDate date) {
        List<SoldItemsDTO> list = soldItemsRepository.testQuery(date);
        double total = 0;
        for (SoldItemsDTO one : list) {
            total += one.getPrice();
        }
        return list.stream().sorted().collect(Collectors.toList());

    }


}
