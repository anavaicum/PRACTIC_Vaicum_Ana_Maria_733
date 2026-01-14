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
}


