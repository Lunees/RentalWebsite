package edugrade.rentalwebsite.services;

import edugrade.rentalwebsite.entities.Car;

import java.util.List;

public class CarWrapper {
    private List<Car> cars;

    public CarWrapper() {
    }

    public CarWrapper(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
