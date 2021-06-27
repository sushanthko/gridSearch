package com.example.gridSearch.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolverTest {

    /**
     * Tests the program output against the corresponding solution file
     */
    @Test
    void solve() throws IOException {

        String solutionFileName = "solution50x50.txt";

        String[] solution = new FileStream(String.format("static/solutions/%s", solutionFileName))
                .getLines()
                .toArray(String[]::new);

        List<String> output = Solver.solve("words.txt", "grid50x50.txt", 0);
        assertArrayEquals(output.toArray(), solution);
    }
}