package com.verycoolapp.idea.rest;

import com.verycoolapp.idea.dto.IdeaDTO;
import com.verycoolapp.idea.service.IdeaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ideas")
public class IdeaResource {

    private final IdeaService ideaService;

    @GetMapping
    public List<IdeaDTO> getAllIdeas() {
        return ideaService.getAllIdeas();
    }

    @PostMapping
    public IdeaDTO createIdea(@Valid @RequestBody final IdeaDTO ideaDTO) {
        return ideaService.createIdea(ideaDTO);
    }
}
