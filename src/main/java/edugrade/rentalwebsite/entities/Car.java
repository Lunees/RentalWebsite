package edugrade.rentalwebsite.entities;

import javax.persistence.*;

@Entity(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private Long carId;

    private String carBrand;

    private String carModel;

    private int dailyPrice;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
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

    public Car(Long carId, String carBrand, String carModel, int dailyPrice) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.dailyPrice = dailyPrice;
    }
}



