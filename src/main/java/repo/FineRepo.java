package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Fine;

import java.nio.file.Path;

public class FineRepo extends AbstractRepoImpl<Fine, Integer> {

    public FineRepo(Path filePath) {
        super(
                filePath,
                Fine::getId,
                new TypeReference<java.util.List<Fine>>() {}
        );
    }
}
