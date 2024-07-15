package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);


    @Query("""
           SELECT c FROM Category c WHERE c.active = true
            """)
    List<Category> findAllActiveCategories();

 }
