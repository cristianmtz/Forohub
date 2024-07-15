package com.aluracursos.forohub.service;


import com.aluracursos.forohub.dto.CategoryDto;
import com.aluracursos.forohub.exception.CategoryNotFoundException;
import com.aluracursos.forohub.mapper.CategoryMapperInterface;
import com.aluracursos.forohub.model.Category;
import com.aluracursos.forohub.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryMapperInterface categoryMapperInterface;

    private final CategoryRepository categoryRepository;



    public CategoryDto saveCategory(CategoryDto categoryDto){

        Category category = categoryMapperInterface.toEntity(categoryDto);

        category.setActive(true);

        return categoryMapperInterface.toDto(categoryRepository.save(category));
    }


    public Category findByName(String name){

        return categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }


    public List<CategoryDto> getAllCategories(){

        return categoryMapperInterface.toListDto(categoryRepository.findAllActiveCategories());
    }

    public CategoryDto updateCategory(String name, CategoryDto categoryDto) {

        Category category = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));


        //Update category
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());


        Category updatedCategory = categoryRepository.save(category);


        return categoryMapperInterface.toDto(updatedCategory);
    }


    public CategoryDto deleteCategory(String name){

        Category category = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));


        category.setActive(false);
        categoryRepository.save(category);


        return categoryMapperInterface.toDto(category);
    }


}
