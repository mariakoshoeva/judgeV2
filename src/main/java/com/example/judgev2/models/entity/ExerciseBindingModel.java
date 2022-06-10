package com.example.judgev2.models.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class ExerciseBindingModel {
    @Length(min = 2,message = "Exercise name length must be more than 2 characters!")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future!")
    private LocalDateTime startedOn;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDateTime dueDate;

    public String getName() {
        return name;
    }

    public ExerciseBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public ExerciseBindingModel setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public ExerciseBindingModel setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
