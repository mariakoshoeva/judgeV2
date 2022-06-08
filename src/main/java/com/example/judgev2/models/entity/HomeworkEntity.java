package com.example.judgev2.models.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "homeworks")
public class HomeworkEntity extends BaseEntity{
    @Column(name = "added_on")
    private LocalDateTime addedOn;
    @Column(name = "git_address")
    private String gitAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity author;
    @ManyToOne(fetch = FetchType.EAGER)
    private ExerciseEntity exercise;

    public HomeworkEntity() {
        this.addedOn = LocalDateTime.now();
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public HomeworkEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkEntity setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public HomeworkEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public HomeworkEntity setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }
}
