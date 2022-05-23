package edugrade.rentalwebsite.entities;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class RentalOrderId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "carId", foreignKey = @ForeignKey(name = "car_carId_FK"))
    private Car carId;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "AccountId",foreignKey = @ForeignKey(name = "UserAccount_AccountId_FK"))
    private UserAccount accountId;


    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public UserAccount getAccountId() {
        return accountId;
    }

    public void setAccountId(UserAccount accountId) {
        this.accountId = accountId;
    }

    public RentalOrderId() {
    }

    public RentalOrderId(Car carId, UserAccount accountId) {
        super();
        this.carId = carId;
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RentalOrderId that = (RentalOrderId) o;
        return carId != null && Objects.equals(carId, that.carId)
                && accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, accountId);
    }
}