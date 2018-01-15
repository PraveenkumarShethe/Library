package com.praveen.shethe.controller;

import com.praveen.shethe.WebIntegrationTestBase;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;

import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerIntegrationTest extends WebIntegrationTestBase {

    private static final String REG = "/register";

    @Test
    public void getAllBooks() throws Exception {

        ClassPathResource resource = new ClassPathResource("registration.json");
        byte[] data = Files.readAllBytes(resource.getFile().toPath());

        mockHttpServletResponse = this.mockMvc
                .perform(post(REG)
                        .header(ORIGIN_HEADER_NAME, ORIGIN_HEADER_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(data))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
    }
}
