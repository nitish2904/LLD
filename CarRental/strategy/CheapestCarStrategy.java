package CarRental.strategy;

import CarRental.model.Car;
import CarRental.model.CarType;
import java.util.Comparator;
import java.util.List;

public class CheapestCarStrategy implements CarSelectionStrategy {
    @Override
    public Car selectCar(List<Car> availableCars, CarType preferred) {
        return availableCars.stream()
                .filter(c -> c.getType() == preferred)
                .min(Comparator.comparing(c -> c.getType().getDailyRate()))
                .or(() -> availableCars.stream().min(Comparator.comparing(c -> c.getType().getDailyRate())))
                .orElse(null);
    }
}
