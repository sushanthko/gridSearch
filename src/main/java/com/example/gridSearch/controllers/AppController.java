package com.example.gridSearch.controllers;

import com.example.gridSearch.model.FormData;
import com.example.gridSearch.utils.Solver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.stream.Collectors;


@Controller
public class AppController {

    @Value("classpath:static/grids/*")
    private Resource[] gridFiles;

    @Value("classpath:static/words/*")
    private Resource[] wordFiles;


    @GetMapping
    public String showForm(Model model) {
        model.addAttribute(
                "gridFileList",
                Arrays.stream(gridFiles).map(Resource::getFilename).collect(Collectors.toList()));
        model.addAttribute("wordFileList",
                Arrays.stream(wordFiles).map(Resource::getFilename).collect(Collectors.toList()));
        model.addAttribute("formData", new FormData());
        return "index";
    }

    @PostMapping
    public String submitForm(@ModelAttribute FormData formData, Model model) {
        model.addAttribute("formData", formData);
        model.addAttribute(
                "result",
                Solver.solve(formData.getWordFile(), formData.getGridFile(), formData.getLength())
        );
        return "result";
    }
}
