package service;

import model.Vehicle;
import repo.AbstractRepoImpl;
import repo.AbstractRepository;

import java.nio.file.Path;
import java.util.List;

public class VehicleService {
    Path path = Path.of("data/vehicles.json");

    AbstractRepository<Vehicle, Integer> vehicleRepo =
            new AbstractRepoImpl<>(
                    path,
                    Vehicle::getId,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Vehicle>>() {}
            );

    //UBUNG 1
    public void printVehiclesCount() {
        long count = vehicleRepo.count();
        System.out.printf("Vehicles loaded: %d%n", count);
    }

    //UBUNG 1
    public List<String> printAllVehicles() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream()
                .map(vehicle -> String.format("[%d] %s | %s | %s | city=%s",
                        vehicle.getId(),
                        vehicle.getLicensePlate(),
                        vehicle.getType(),
                        vehicle.getStatus(),
                        vehicle.getOwnerCity()))
                .toList();
    }

    //UBUNG 2
    public List<String> filterVehicleByTypeUndStatus(String type, String status) {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream()
                .filter(f -> f.getType().equals(type))
                .filter(f -> f.getStatus().equals(status))
                .map(vehicle -> String.format("[%d] %s | %s | %s | city=%s",
                        vehicle.getId(),
                        vehicle.getLicensePlate(),
                        vehicle.getType(),
                        vehicle.getStatus(),
                        vehicle.getOwnerCity()))
                .toList();
    }



    //UBUNG 3
    public List<String> sortBySkillDescThenNameAsc() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream()
                .sorted((f1, f2) -> {
                    return f1.getOwnerCity().compareToIgnoreCase(f2.getOwnerCity());


                })
                .map(vehicle -> String.format("[%d] %s | %s | %s | city=%s",
                        vehicle.getId(),
                        vehicle.getLicensePlate(),
                        vehicle.getType(),
                        vehicle.getStatus(),
                        vehicle.getOwnerCity()))
                .toList();
    }

    //UBUNG 4
    public void saveSortedVehicleToFile() {
        List<String> sortedVehicle = sortBySkillDescThenNameAsc();
        Path filePath = Path.of("data/vehicles_sorted.txt");
        try {
            java.nio.file.Files.write(filePath, sortedVehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


