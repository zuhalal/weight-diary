package com.zuhal.weightdiary.weight.repository;


import com.zuhal.weightdiary.weight.model.WeightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightDb extends JpaRepository<WeightModel, Long> {
    @Query("SELECT w FROM WeightModel w ORDER BY w.date DESC ")
    List<WeightModel> findAllOrderByDate();
}
