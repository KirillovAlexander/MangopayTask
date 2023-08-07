package com.verycoolapp.rest;

import com.verycoolapp.idea.rest.IdeaResource;
import com.verycoolapp.idea.service.IdeaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IdeaResource.class)
class IdeaResourceTest {

    @MockBean
    IdeaService ideaService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void givenRequestIsAnonymous_whenGetIdeas_thenUnauthorized() {
        mockMvc.perform(get("/api/ideas")
                        .with(anonymous()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @SneakyThrows
    void givenRequestIsAuthorized_whenGetIdeas_thenReturnIdeas() {
        mockMvc.perform(get("/api/ideas")
                        .with(oauth2Login()))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void givenRequestIsAnonymous_whenPostIdeas_thenUnauthorized() {
        mockMvc.perform(post("/api/ideas")
                        .with(csrf())
                        .with(anonymous())
                        .content(getRequestBody("json/idea.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @SneakyThrows
    void givenRequestIsAuthorized_whenPostIdeas_thenOk() {
        mockMvc.perform(post("/api/ideas")
                        .with(csrf())
                        .with(oauth2Login())
                        .content(getRequestBody("json/idea.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void givenRequestIsAuthorized_whenPostIdeasWithEmptyFields_thenBadRequest() {
        mockMvc.perform(post("/api/ideas")
                        .with(csrf())
                        .with(oauth2Login())
                        .content(getRequestBody("json/idea-empty-fields.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private String getRequestBody(final String path) throws IOException {
        var resource = new ClassPathResource(path);
        var inputStream = resource.getInputStream();
        return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    }
}
