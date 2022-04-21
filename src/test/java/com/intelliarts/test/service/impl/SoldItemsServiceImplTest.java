package com.intelliarts.test.service.impl;

import com.intelliarts.test.TestApplication;
import com.intelliarts.test.dto.SoldItemsDTO;
import com.intelliarts.test.model.SoldItems;
import com.intelliarts.test.repository.SoldItemsRepository;
import com.intelliarts.test.service.SoldItemsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = TestApplication.class)
class SoldItemsServiceImplTest {
    @Mock
    SoldItemsRepository soldItemsRepository;
    SoldItemsService soldItemsService;

    @BeforeEach
    void setUp() {
        soldItemsService = new SoldItemsServiceImpl(soldItemsRepository);
    }

    @Test
    void totalForMonth() {
        List<SoldItems> soldItems = initSoldItems();
        Mockito.when(soldItemsRepository.findAll()).thenReturn(soldItems);
        soldItemsService.totalForMonth((YearMonth.of(2022, 1)));

    }

    @Test
    void reportSinceDateSuccessfully() {
        List<SoldItemsDTO> soldItemsDTOS = initSoldItemsDTO();
        Mockito.when(soldItemsRepository.allSoldSinceDate(LocalDate.now())).thenReturn(soldItemsDTOS);
        soldItemsService.reportSinceDate(LocalDate.now());
    }

    @Test
    void reportSinceDateThrow() {
        List<SoldItemsDTO> list = new ArrayList<>();
        Mockito.when(soldItemsRepository.allSoldSinceDate(LocalDate.now())).thenReturn(list);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> soldItemsService.reportSinceDate(LocalDate.now()));
        Assertions.assertEquals("there were not sold items from date " + LocalDate.now(), exception.getMessage());
    }

    @Test
    void createSuccessfully() {
        SoldItems soldItems = initSoldItems().get(1);
        soldItemsService.create(soldItems);
        Mockito.verify(soldItemsRepository, Mockito.times(1)).save(soldItems);
    }


    static List<SoldItemsDTO> initSoldItemsDTO() {
        return new ArrayList<>(Arrays.asList(
                new SoldItemsDTO("Test1", 22.3, 10L),
                new SoldItemsDTO("Test2", 22.3, 8L)));
    }

    static List<SoldItems> initSoldItems() {
        return new ArrayList<>(Arrays.asList(
                new SoldItems(1L, "Test1", 2.2, LocalDate.of(2022, 12, 3), 33),
                new SoldItems(2L, "Test2", 2.2, LocalDate.of(2012, 12, 12), 12)

        ));
    }
}