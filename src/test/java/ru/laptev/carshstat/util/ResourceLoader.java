package ru.laptev.carshstat.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ResourceLoader {
    public String getResourceAsString(String filePath) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String resultString = null;
        try {
            resultString = new String(Files.readAllBytes(Paths.get(classLoader.getResource(filePath).toURI())));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return resultString;
    }
}
