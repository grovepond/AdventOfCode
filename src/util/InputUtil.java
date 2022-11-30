package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class InputUtil {

    public static List<String> getInputAsStringList (String fileName) {
        String basePath = "/Users/fredrik.hultmar/tmp/aoc/2022/";
        try {
            return Files.readAllLines(new File(basePath + fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

