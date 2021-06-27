package com.example.gridSearch.utils;

import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This program reads reads two files and prints out the output words from a character grid
 */
public class Solver {

    public static List<String> solve(String wordFileName, String gridFileName, int minlength) {

        List<String> output = new ArrayList<>();
        try {
            // read all words
            // filter out words shorter than length argument
            Path wordFilePath = ResourceUtils.getFile(
                    String.format("classpath:static/words/%s", wordFileName)).toPath();
            Set<String> wordSet = Files.lines(wordFilePath)
                    .filter(x -> x.length() >= minlength).collect(Collectors.toSet());

            // read the character grid
            Path gridFilePath = ResourceUtils.getFile(
                    String.format("classpath:static/grids/%s", gridFileName)).toPath();
            char[][] grid = Files.lines(gridFilePath)
                    .map(String::toCharArray).toArray((char[][]::new));

            List<String> words = new ArrayList<>(wordSet);
            output = Solution.runSolution(grid, words);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument.");
            e.printStackTrace();
        }
        return output;
    }
}
