package com.example.judgev2.models.binding;

import javax.validation.constraints.Pattern;

public class HomeworkAddBindingModel {
    private String exercise;
    @Pattern(regexp = "https:\\/\\/?github\\.com\\/.+",message = "Enter git address following this pattern : https:/github.com/{username}/SpringTestData/…" )
    private String gitAddress;

    public String getExercise() {
        return exercise;
    }

    public HomeworkAddBindingModel setExercise(String exercise) {
        this.exercise = exercise;
        return this;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkAddBindingModel setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }
}
