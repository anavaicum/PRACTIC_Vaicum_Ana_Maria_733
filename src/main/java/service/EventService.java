package service;

import model.Event;
import repo.AbstractRepoImpl;
import repo.AbstractRepository;

import java.nio.file.Path;
import java.util.List;

public class EventService {

    Path path = Path.of("data/events.json");

    AbstractRepository<Event, Integer> eventRepo =
            new AbstractRepoImpl<>(
                    path,
                    Event::getId,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Event>>() {}
            );

    //UBUNG 1
    public void printEventsCount() {
        long count = eventRepo.count();
        System.out.printf("Events loaded: %d%n", count);
    }


}
