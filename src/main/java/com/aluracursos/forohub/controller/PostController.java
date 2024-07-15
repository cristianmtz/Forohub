package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.dto.PostDto;
import com.aluracursos.forohub.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PostController {


    private final PostService postService;


    @Operation(
            summary = "Create post",
            description = "Allows the user to create a new post with the specified username, category and post details"
    )
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestParam String username, @RequestParam String category, @Valid @RequestBody PostDto postDto){
        PostDto createdPost = postService.createPost(username, category, postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @Operation(
            summary = "Get posts by username",
            description = "Retrieves a list of posts created by the specified username"
    )
    @GetMapping("by-username/{username}")
    public ResponseEntity<List<PostDto>> getPostsByUsername(@PathVariable String username) {
        List<PostDto> posts = postService.getPostsByUsername(username);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "Get posts by category",
            description = "Retrieves a list of posts in the specified category."
    )
    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable String category){

        List<PostDto> posts = postService.getPostsByCategory(category);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}
