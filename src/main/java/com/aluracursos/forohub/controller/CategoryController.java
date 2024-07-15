package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.dto.CategoryDto;
import com.aluracursos.forohub.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class CategoryController {

    private final CategoryService categoryService;


    @Operation(
            summary = "Add a new category",
            description = "Allows the user to add a new category."
    )
    @PostMapping("/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid CategoryDto categoryDto){

       CategoryDto categoryAdded =  categoryService.saveCategory(categoryDto);
       return new ResponseEntity<>(categoryAdded, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get all categories",
            description = "Allows obtain all categories available"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        List<CategoryDto> categoryDtos =  categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }


    @Operation(
            summary = "Update category",
            description = "Allows you to update a specific category by sending the name"
    )
    @PostMapping("/update/{name}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String name, @RequestBody @Valid CategoryDto categoryDto){
        CategoryDto updatedCategory = categoryService.updateCategory(name, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }



    @Operation(
            summary = "Delete category",
            description = "Allow deleting a category by its name (Logic delete: Active=false)"
    )
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable String name){

        CategoryDto categoryDto = categoryService.deleteCategory(name);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }


}
