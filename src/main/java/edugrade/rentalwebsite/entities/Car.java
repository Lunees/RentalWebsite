package edugrade.rentalwebsite.entities;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private Integer carId;

    private String carBrand;

    private String carModel;

    private int dailyPrice;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalOrder> rentalOrders = new ArrayList<>();

    public List<RentalOrder> getRentalOrders() {
        return rentalOrders;
    }

    public void setRentalOrders(List<RentalOrder> rentalOrders) {
        this.rentalOrders = rentalOrders;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Car() {
    }

    public Car(Integer carId, String carBrand, String carModel, int dailyPrice) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.dailyPrice = dailyPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return carId != null && Objects.equals(carId, car.carId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
