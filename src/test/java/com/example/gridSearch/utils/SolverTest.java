package com.example.gridSearch.utils;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolverTest {

    /**
     * Tests the program output against the corresponding solution file
     */
    @Test
    void solve() throws IOException {

        String solutionFileName = "solution50x50.txt";

        Path solutionFilePath = ResourceUtils.getFile(
                String.format("classpath:static/solutions/%s", solutionFileName)).toPath();
        String[] solution = Files.lines(solutionFilePath).toArray(String[]::new);

        List<String> output = Solver.solve("words.txt", "grid50x50.txt", 0);
        assertArrayEquals(output.toArray(), solution);
    }
}