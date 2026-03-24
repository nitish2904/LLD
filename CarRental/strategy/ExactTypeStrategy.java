package CarRental.strategy;

import CarRental.model.Car;
import CarRental.model.CarType;
import java.util.List;

public class ExactTypeStrategy implements CarSelectionStrategy {
    @Override
    public Car selectCar(List<Car> availableCars, CarType preferred) {
        return availableCars.stream().filter(c -> c.getType() == preferred).findFirst().orElse(null);
    }
}
