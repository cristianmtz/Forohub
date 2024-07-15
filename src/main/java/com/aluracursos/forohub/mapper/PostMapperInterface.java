package com.aluracursos.forohub.mapper;


import com.aluracursos.forohub.dto.PostDto;
import com.aluracursos.forohub.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface PostMapperInterface {

    PostMapperInterface mapper = Mappers.getMapper(PostMapperInterface.class);

    Post toEntity(PostDto postDto);

    PostDto toDto(Post post);

    List<PostDto> toListDto(List<Post> postList);
}
