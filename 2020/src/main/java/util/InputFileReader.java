package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class InputFileReader {

    public static <T> Stream<T> readObjects(String fileName, Function<String, T> objectMapper) throws IOException {
        return readStrings(fileName).map(objectMapper::apply);
    }

    public static Stream<Integer> readInts(String fileName) throws IOException {
        return readObjects(fileName, Integer::parseInt);
    }

    public static Stream<String> readStrings(String fileName) throws IOException {
        Path path = Paths.get(new File("inputs/" + fileName).getPath());
        return Files.readAllLines(path).stream();
    }
}
