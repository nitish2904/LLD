package CarRental.strategy;

import CarRental.model.Car;
import CarRental.model.CarType;
import java.util.List;

public interface CarSelectionStrategy {
    Car selectCar(List<Car> availableCars, CarType preferred);
}
