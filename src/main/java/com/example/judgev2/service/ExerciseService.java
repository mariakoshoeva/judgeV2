package com.example.judgev2.service;

import com.example.judgev2.models.binding.ExerciseBindingModel;
import com.example.judgev2.models.entity.ExerciseEntity;

import java.util.List;

public interface ExerciseService {
    boolean add(ExerciseBindingModel exerciseBindingModel);

    List<String> findAllName();

    boolean checkHomeworkIsOutOfDate(String exercise);

    ExerciseEntity findByName(String exercise);
}


