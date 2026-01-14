package service;

import model.Fine;
import repo.AbstractRepoImpl;
import repo.AbstractRepository;

import java.nio.file.Path;
import java.util.List;

public class FineService {
    Path path = Path.of("data/fines.json");

    AbstractRepository<Fine, Integer> fineRepo =
            new AbstractRepoImpl<>(
                    path,
                    Fine::getId,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Fine>>() {}
            );

    //UBUNG 1
    public void printFinesCount() {
        long count = fineRepo.count();
        System.out.printf("Fines loaded: %d%n", count);
    }
}
