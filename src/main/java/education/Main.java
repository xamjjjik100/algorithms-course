package education;

import education.peoplestream.PeopleStream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // тестовый для проверки
            URL resource = Main.class.getClassLoader().getResource("people.txt");
            if (resource == null) {
                throw new IllegalArgumentException("Файл не найден!");
            }

            Path path = Path.of(resource.toURI());

            Map<Integer, List<String>> result = PeopleStream.stream(path);
            System.out.println(result);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
