package com.example.gridSearch.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Stream;


/**
 * Utility class to get stream of Strings from a given filepath
 */
public class FileStream {

    private final String filePath;

    public FileStream(String filePath) {
        this.filePath = filePath;
    }

    public Stream<String> getLines() throws IOException {
        URL url = new ClassPathResource(filePath).getURL();
        return new BufferedReader(new InputStreamReader(url.openStream())).lines();
    }
}
