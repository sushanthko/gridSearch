package com.example.gridSearch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Integration Test - test the rest controller
 **/
@ExtendWith(SpringExtension.class)
@WebMvcTest(RESTController.class)
class RESTControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getResult() throws Exception {

        String wordFileName = "words.txt";
        String gridFileName = "grid100x100.txt";
        int length = 0;
        String solutionFileName = "solution100x100.txt";

        Path solutionFilePath = ResourceUtils.getFile(
                String.format("classpath:static/solutions/%s", solutionFileName)).toPath();
        String[] solution = Files.lines(solutionFilePath).toArray(String[]::new);

        String solutionResult = new ObjectMapper().writeValueAsString(solution);

        mvc.perform(post(String.format("/solve?words=%s&grid=%s&length=%s", wordFileName, gridFileName, length)))
                .andExpect(content().string(solutionResult));

    }
}