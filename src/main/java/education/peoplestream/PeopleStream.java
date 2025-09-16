package education.peoplestream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PeopleStream {

    public static Map<Integer, List<String>> stream(Path path) throws IOException {
        Map<Integer, List<String>> result = new ConcurrentHashMap<>();

        Files.lines(path)
                .parallel()
                .map(s -> s.trim())
                .map(s -> s.split(":", 2))
                .filter(parts -> parts.length == 2 && !parts[1].isBlank())
                .forEach(parts -> {
                    int key = Integer.parseInt(parts[1].trim());
                    String name = capitalize(parts[0].trim().toLowerCase());

                    result.computeIfAbsent(key, k -> new ArrayList<>()).add(name);
                });

        return result;
    }

    // Доп метод
    private static String capitalize(String name) {
        if (name.isEmpty())
            return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
