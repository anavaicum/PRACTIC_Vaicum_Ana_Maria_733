package controller;

import service.VehicleService;
import service.FineService;
import service.EventService;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {

    private final VehicleService vehicleService;
    private final FineService fineService;
    private final EventService eventService;

    private final Scanner scanner = new Scanner(System.in);

    // folosim LinkedHashMap ca să păstrăm ordinea meniului
    private final Map<Integer, MenuItem> menu = new LinkedHashMap<>();

    public ConsoleController(VehicleService vehicleService, FineService fineService, EventService eventService)
    {
       this.vehicleService = vehicleService;
       this.fineService = fineService;
       this.eventService = eventService;

        registerMenu();
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = readInt("Choose option: ");

            if (choice == 0) {
                System.out.println("Bye!");
                return;
            }

            MenuItem item = menu.get(choice);
            if (item == null) {
                System.out.println("Invalid option.");
                continue;
            }

            System.out.println("--------------------------------------------------------------");
            try {
                item.action.run();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                // debug:
                // e.printStackTrace();
            }
            System.out.println("--------------------------------------------------------------");
        }
    }

    // -------------------- MENU REGISTRATION --------------------

    private void registerMenu() {

        // 1
        menu.put(1, new MenuItem("Ubung 1", this::option1));


    }

    private void printMenu() {
        System.out.println();
        System.out.println("============== FORMULA 1 CONSOLE ==============");
        menu.forEach((k, v) -> System.out.printf("%d. %s%n", k, v.label));
        System.out.println("0. Exit");
        System.out.println("==================================================");
    }



    // -------------------- OPTIONS (1..7) --------------------


    private void option1() {

        vehicleService.printVehiclesCount();
        fineService.printFinesCount();
        eventService.printEventsCount();

        vehicleService.printAllVehicles().forEach(System.out::println);
    }






    // -------------------- INPUT HELPERS --------------------

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }


    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Value must not be empty.");
        }
    }

    // -------------------- SMALL MENU ITEM MODEL --------------------

    private static class MenuItem {
        final String label;
        final Runnable action;

        MenuItem(String label, Runnable action) {
            this.label = label;
            this.action = action;
        }
    }

}
