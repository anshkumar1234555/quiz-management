package com.ansh.quiz_management.repository;

import com.ansh.quiz_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}