package com.example.judgev2.service.impl;

import com.example.judgev2.models.entity.ExerciseBindingModel;
import com.example.judgev2.models.entity.ExerciseEntity;
import com.example.judgev2.repositoty.ExerciseRepository;
import com.example.judgev2.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(ExerciseBindingModel exerciseBindingModel) {
        if(exerciseRepository.existsByName(exerciseBindingModel.getName())){
            return false;
        }
        ExerciseEntity exerciseEntity = modelMapper.map(exerciseBindingModel, ExerciseEntity.class);

        this.exerciseRepository.save(exerciseEntity);
        return true;
    }

}
