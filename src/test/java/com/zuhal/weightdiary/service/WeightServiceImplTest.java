package com.zuhal.weightdiary.service;

import com.zuhal.weightdiary.weight.model.WeightModel;
import com.zuhal.weightdiary.weight.repository.WeightDb;
import com.zuhal.weightdiary.weight.service.WeightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class WeightServiceImplTest {
    @InjectMocks
    private WeightServiceImpl weightService;
    @Mock
    WeightDb weightDb;

    private final WeightModel weight1 = new WeightModel(1L, LocalDate.parse("2022-12-02"), 20L, 4L, LocalDateTime.parse("2023-06-20T12:30"));
    private final WeightModel weight2 = new WeightModel(2L, LocalDate.parse("2022-09-01"), 60L, 5L, LocalDateTime.parse("2023-05-20T11:20"));
    private List<WeightModel> weightList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        weightList.add(weight1);
        weightList.add(weight2);
        weightDb.save(weight1);
        weightDb.save(weight2);
    }

    @Test
    void getAllWeightTest() {
        lenient().when(weightDb.findAllOrderByDate()).thenReturn(weightList);
        List<WeightModel> getAllWeight = weightService.getAllWeight();
        assertEquals(getAllWeight, weightList);
    }

    @Test
    void getOneWeightTest() {
        lenient().when(weightDb.findById(1L)).thenReturn(Optional.of(weight1));
        WeightModel weightIdOne = weightService.getWeight(1L);
        assertEquals(weightIdOne, weight1);
    }
}
