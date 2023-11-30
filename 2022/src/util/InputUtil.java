package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class InputUtil {
    static String basePath = System.getProperty("user.dir") + "/input/aoc22/";

    public static List<String> getInputAsStringList (String fileName) {
        try {
            return Files.readAllLines(new File(basePath + fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getInputAsString (String fileName) {
        try {
            return Files.readString(new File(basePath + fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

