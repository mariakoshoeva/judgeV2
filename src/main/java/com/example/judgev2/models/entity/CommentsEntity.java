package com.example.judgev2.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentsEntity extends BaseEntity {

    private Integer score;
    @Column(name = "text_content", columnDefinition = "text")
    private String textContent;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private HomeworkEntity homework;

    public Integer getScore() {
        return score;
    }

    public CommentsEntity setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentsEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentsEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public HomeworkEntity getHomework() {
        return homework;
    }

    public CommentsEntity setHomework(HomeworkEntity homework) {
        this.homework = homework;
        return this;
    }
}
