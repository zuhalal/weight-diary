package com.zuhal.weightdiary.weight.service;

import com.zuhal.weightdiary.weight.model.WeightModel;
import org.springframework.beans.factory.annotation.Autowired;
import com.zuhal.weightdiary.weight.repository.WeightDb;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WeightServiceImpl implements WeightService {
    @Autowired
    WeightDb weightDb;

    @Override
    public List<WeightModel> getAllWeight() {
        return weightDb.findAllOrderByDate();
    }

    @Override
    public WeightModel getWeight(Long id) {
        Optional<WeightModel> weight = weightDb.findById(id);

        return weight.orElse(null);
    }

    @Override
    public WeightModel createWeight(WeightModel weightModel) {
        return weightDb.save(weightModel);
    }

    @Override
    public WeightModel updateWeight(WeightModel weightModel) {
        return weightDb.save(weightModel);
    }

    @Override
    public WeightModel deleteWeight(WeightModel weightModel) {
        weightDb.delete(weightModel);
        return weightModel;
    }
}
