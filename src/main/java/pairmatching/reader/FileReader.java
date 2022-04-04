package pairmatching.reader;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileReader {
    public List<String> getLines(String fileName) {
        try {
            URL resource = getClass().getResource(fileName);

            if (resource != null) {
                return Files.readAllLines(Paths.get(resource.getFile().substring(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
