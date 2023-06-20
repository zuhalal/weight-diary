package com.zuhal.weightdiary.weight.service;

import com.zuhal.weightdiary.weight.model.WeightModel;

import java.util.List;

public interface WeightService {
    List<WeightModel> getAllWeight();
    WeightModel getWeight(Long id);
    WeightModel createWeight(WeightModel weightModel);
    WeightModel updateWeight(WeightModel weightModel);
    WeightModel deleteWeight(WeightModel weightModel);
}