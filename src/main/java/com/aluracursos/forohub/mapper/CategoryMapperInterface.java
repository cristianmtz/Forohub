package com.aluracursos.forohub.mapper;


import com.aluracursos.forohub.dto.CategoryDto;
import com.aluracursos.forohub.dto.PostDto;
import com.aluracursos.forohub.model.Category;
import com.aluracursos.forohub.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapperInterface {

    CategoryMapperInterface mapper = Mappers.getMapper(CategoryMapperInterface.class);


    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    List<CategoryDto> toListDto(List<Category> categoryList);


}
