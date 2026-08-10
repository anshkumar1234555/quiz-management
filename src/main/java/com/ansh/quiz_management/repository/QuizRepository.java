package com.ansh.quiz_management.repository;

import com.ansh.quiz_management.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}