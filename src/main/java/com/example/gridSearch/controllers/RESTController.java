package com.example.gridSearch.controllers;

import com.example.gridSearch.utils.Solver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RESTController {

    @PostMapping("/solve")
    public Collection<String> getResult(@RequestParam String words, @RequestParam String grid,
                                        @RequestParam int length) {
        return Solver.solve(words, grid, length);
    }
}
