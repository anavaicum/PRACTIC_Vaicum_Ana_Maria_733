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


    //UBUNG 5
    public List<String> calculateAndPrintEventPoints() {
        List<Event> events = eventRepo.findAll().stream().limit(5).toList();
        return events.stream().map(event -> {
            Integer riskScore = switch (event.getType()) {
                case SPEEDING -> event.getSeverity() * 2;
                case RED_LIGHT -> event.getSeverity() * 3;
                case ACCIDENT -> event.getSeverity() * 5;
                case PRIORITY_PASS -> event.getSeverity() * 1;

            };
            return String.format("Event %d -> severity=%d -> riskScore=%d",
                    event.getId(),
                    event.getSeverity(),
                    riskScore);
        }).toList();
    }



}
