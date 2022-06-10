package com.example.judgev2.repositoty;

import com.example.judgev2.models.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity,Long> {
}
