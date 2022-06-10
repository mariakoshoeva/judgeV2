package com.example.judgev2.repositoty;

import com.example.judgev2.models.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {
    boolean existsByName(String name);
}
