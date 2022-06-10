package com.example.judgev2.repositoty;

import com.example.judgev2.models.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {
    boolean existsByName(String name);
    @Query("select e.name from ExerciseEntity e group by e.startedOn")
    List<String> findAllNames();

    @Query("select (e.dueDate < current_timestamp) from ExerciseEntity e where e.name = ?1")
    boolean checkHomeworkIsOnDate(String name);

    ExerciseEntity findByName(String exercise);
}
