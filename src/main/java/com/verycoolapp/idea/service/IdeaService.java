package com.verycoolapp.idea.service;

import com.verycoolapp.idea.dto.IdeaDTO;
import com.verycoolapp.idea.repository.IdeaRepository;
import com.verycoolapp.idea.service.mapper.IdeaMapper;
import com.verycoolapp.idea.util.PrincipalUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final IdeaMapper ideaMapper;

    public List<IdeaDTO> getAllIdeas(final Principal principal) {
        var email = PrincipalUtils.getPrincipalEmail(principal);
        log.debug("Searching for ideas for the user: {}", email);
        var ideas = ideaRepository.findAllByCreatedBy(email);
        log.debug("The following ideas are found for the user: {}\n{}", email, ideas);
        return ideaMapper.convert(ideas);
    }

    public IdeaDTO createIdea(final IdeaDTO ideaDTO, final Principal principal) {
        var email = PrincipalUtils.getPrincipalEmail(principal);
        log.debug("Saving the idea: {}\nfor the user: {}", ideaDTO, email);
        var idea = ideaRepository.save(ideaMapper.convert(ideaDTO));
        return ideaMapper.convert(idea);
    }
}
