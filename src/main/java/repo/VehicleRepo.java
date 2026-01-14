package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Vehicle;

import java.nio.file.Path;

public class VehicleRepo extends AbstractRepoImpl<Vehicle, Integer> {

    public VehicleRepo(Path filePath) {
        super(
                filePath,
                Vehicle::getId,
                new TypeReference<java.util.List<Vehicle>>() {}
        );
    }
}
