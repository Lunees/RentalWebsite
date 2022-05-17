package edugrade.rentalwebsite.entities;

public class RentOrderWrapper {

    Integer carId;
    Integer accountId;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public RentOrderWrapper() {
    }

    public RentOrderWrapper(Integer carId, Integer accountId) {
        this.carId = carId;
        this.accountId = accountId;
    }
}
