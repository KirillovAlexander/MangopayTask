package com.verycoolapp.idea.rest;

import com.verycoolapp.idea.dto.IdeaDTO;
import com.verycoolapp.idea.service.IdeaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
