package com.verycoolapp.idea.service;

import com.verycoolapp.idea.dto.IdeaDTO;
import com.verycoolapp.idea.repository.IdeaRepository;
import com.verycoolapp.idea.service.mapper.IdeaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final IdeaMapper ideaMapper;

    public List<IdeaDTO> getAllIdeas() {
        var ideas = ideaRepository.findAll();
        return ideaMapper.convert(ideas);
    }
}
