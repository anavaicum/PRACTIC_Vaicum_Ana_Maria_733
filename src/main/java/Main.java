import controller.ConsoleController;

import service.EventService;

import service.FineService;

import service.VehicleService;

public class Main {
    public static void main(String[] args) {
        var controller = new ConsoleController(
                new VehicleService(),
                new FineService(),
                new EventService()
        );
        controller.run();
    }
}
