package kram.advent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class FileHelper {

    private static final ClassLoader cl = FileHelper.class.getClassLoader();

    public static String readStringFromFile(String file) {
        try {
            URI uri = cl.getResource(file).toURI();
            return Files.readString(Path.of(uri), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readListFromFile(String file) {
        try {
            URI uri = cl.getResource(file).toURI();
            return Files.readAllLines(Path.of(uri), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeStringToFile(String file, String text) {
        try {
            URI uri = Objects.requireNonNull(cl.getResource(file)).toURI();
            Files.writeString(Path.of(uri), text, StandardOpenOption.WRITE);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
