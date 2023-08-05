package com.verycoolapp.idea.service.mapper;

import com.verycoolapp.idea.domain.Idea;
import com.verycoolapp.idea.dto.IdeaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdeaMapper {

    List<IdeaDTO> convert(final List<Idea> ideas);
}
