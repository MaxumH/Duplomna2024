package mvc.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileService {
    public List<String> readFile(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("files/" + filename);
        Path path = resource.getFile().toPath();
        return Files.readAllLines(path);
    }
}