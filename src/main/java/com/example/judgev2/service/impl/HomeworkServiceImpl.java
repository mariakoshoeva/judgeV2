package com.example.judgev2.service.impl;

import com.example.judgev2.models.binding.HomeworkAddBindingModel;
import com.example.judgev2.models.entity.ExerciseEntity;
import com.example.judgev2.models.entity.HomeworkEntity;
import com.example.judgev2.models.entity.UserEntity;
import com.example.judgev2.repositoty.HomeworkRepository;
import com.example.judgev2.security.CurrentUser;
import com.example.judgev2.service.ExerciseService;
import com.example.judgev2.service.HomeworkService;
import com.example.judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public void add(HomeworkAddBindingModel homeworkAddBindingModel) {
        HomeworkEntity homework = new HomeworkEntity();
        homework.setGitAddress(homeworkAddBindingModel.getGitAddress());
        ExerciseEntity exercise = exerciseService.findByName(homeworkAddBindingModel.getExercise());
        homework.setExercise(exercise);
        UserEntity userEntity = userService.findById(currentUser.getId());
        homework.setAuthor(userEntity);
        homeworkRepository.save(homework);
    }
}
