package com.ansh.quiz_management.repository;

import com.ansh.quiz_management.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

}