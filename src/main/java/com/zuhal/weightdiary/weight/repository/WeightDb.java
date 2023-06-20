package com.zuhal.weightdiary.weight.repository;


import com.zuhal.weightdiary.weight.model.WeightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightDb extends JpaRepository<WeightModel, Long> {
    List<WeightModel> findAll();
}
