package com.verycoolapp.idea.rest;

import com.verycoolapp.idea.dto.IdeaDTO;
import com.verycoolapp.idea.service.IdeaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ideas")
public class IdeaResource {

    private final IdeaService ideaService;

    @Operation(summary = "Get all ideas for the current user")
    @GetMapping
    public List<IdeaDTO> getAllIdeas(final Principal principal) {
        return ideaService.getAllIdeas(principal);
    }

    @Operation(summary = "Create a new idea for the current user")
    @PostMapping
    public IdeaDTO createIdea(@Valid @RequestBody final IdeaDTO ideaDTO, final Principal principal) {
        return ideaService.createIdea(ideaDTO, principal);
    }
}
