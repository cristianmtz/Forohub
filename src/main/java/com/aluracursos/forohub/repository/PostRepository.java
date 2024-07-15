package com.aluracursos.forohub.repository;


import com.aluracursos.forohub.model.Category;
import com.aluracursos.forohub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT p FROM Post p WHERE p.user.username = :username")
    List<Post> findAllPostByUsername(String username);


    @Query("SELECT p FROM Post p WHERE p.category.name=:category")
    List<Post> findAllPostByCategory(String category);

}
