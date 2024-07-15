package com.aluracursos.forohub.service;


import com.aluracursos.forohub.dto.PostDto;
import com.aluracursos.forohub.exception.NoPostsFoundException;
import com.aluracursos.forohub.mapper.PostMapperInterface;
import com.aluracursos.forohub.model.Category;
import com.aluracursos.forohub.model.Post;
import com.aluracursos.forohub.model.User;
import com.aluracursos.forohub.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapperInterface postMapperInterface;

    private final PostRepository postRepository;

    private final UserService userService;

    private final CategoryService categoryService;


    @Transactional
    public PostDto createPost(String username, String category, PostDto postDto){

        // Convertir PostDto a entidad Post
        Post post = postMapperInterface.toEntity(postDto);

        // Obtener la categoría y asociarla al post
        Category entityCategory = categoryService.findByName(category);
        post.setCategory(entityCategory);

        // Guardar el post
        Post createdPost = postRepository.save(post);

        //Convertir la entidad Post a PostDto para devolverla
        return postMapperInterface.toDto(createdPost);
    }

    @Transactional
    public List<PostDto> getPostsByUsername(String username) {

        List<Post> posts = postRepository.findAllPostByUsername(username);

        if (posts.isEmpty()){
            throw new NoPostsFoundException("No posts found for username: " + username);
        }

        return postMapperInterface.toListDto(posts);
    }


    @Transactional
    public List<PostDto> getPostsByCategory(String category){

        Category categoryEntity = categoryService.findByName(category);


        if(categoryEntity == null){
            throw new RuntimeException("Category not found");
        }

        return postMapperInterface.toListDto(postRepository.findAllPostByCategory(category));

    }


}
