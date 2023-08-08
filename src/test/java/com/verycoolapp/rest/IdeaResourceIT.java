package com.verycoolapp.rest;

import com.verycoolapp.base.BaseTest;
import com.verycoolapp.idea.domain.Idea;
import com.verycoolapp.idea.repository.IdeaRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class IdeaResourceIT extends BaseTest {

    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String GROUP = "Group";
    private static final String EMAIL = "testcase@test.test";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IdeaRepository ideaRepository;

    @BeforeEach
    public void init() {
        ideaRepository.deleteAll();
    }

    @SneakyThrows
    @Test
    void givenIdea_whenGetIdeas_thenReturnIdea() {
        var idea = new Idea();
        idea.setTitle(TITLE);
        idea.setDescription(DESCRIPTION);
        idea.setGroup(GROUP);
        idea.setCreatedBy(EMAIL);
        ideaRepository.save(idea);

        mockMvc.perform(get("/api/ideas")
                        .with(oauth2Login()
                                .attributes(attributes -> attributes.put("email", EMAIL))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].id", not(empty())))
                .andExpect(jsonPath("[0].title").value(TITLE))
                .andExpect(jsonPath("[0].description").value(DESCRIPTION))
                .andExpect(jsonPath("[0].group").value(GROUP));
    }

    @SneakyThrows
    @Test
    void givenUserWithoutEmail_whenGetIdeas_thenError() {
        mockMvc.perform(get("/api/ideas")
                        .with(oauth2Login()))
                .andExpect(status().isInternalServerError());
    }

    @SneakyThrows
    @Test
    void givenRequest_whenCreateIdea_thenIdeaCreated() {
        var now = Instant.now();
        mockMvc.perform(post("/api/ideas")
                        .with(csrf())
                        .with(oauth2Login()
                                .attributes(attributes -> attributes.put("email", EMAIL)))
                        .content(getResourceAsString("json/idea.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var ideas = ideaRepository.findAll();
        assertThat(ideas).hasSize(1);
        assertThat(ideas.get(0).getTitle()).isEqualTo("My Great Idea");
        assertThat(ideas.get(0).getDescription()).isEqualTo("This is a fantastic idea that will change the world!");
        assertThat(ideas.get(0).getGroup()).isEqualTo("Innovations");
        assertThat(ideas.get(0).getCreatedBy()).isEqualTo(EMAIL);
        assertThat(ideas.get(0).getLastModifiedBy()).isEqualTo(EMAIL);
        assertThat(ideas.get(0).getCreatedDate()).isAfter(now);
        assertThat(ideas.get(0).getLastModifiedDate()).isAfter(now);
    }

    @SneakyThrows
    @Test
    void givenUserWithoutEmail_whenCreateIdea_thenError() {
        mockMvc.perform(post("/api/ideas")
                        .with(oauth2Login())
                .content(getResourceAsString("json/idea.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
