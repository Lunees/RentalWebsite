package edugrade.rentalwebsite.entities;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class RentalOrderId implements Serializable {

@Column(name = "car_Id",updatable = false,insertable = false)
private Integer carId;
@Column(name = "account_Id",updatable = false,insertable = false)
private Integer accountId;

    public long getCarId() {
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



    public RentalOrderId() {
    }

    public RentalOrderId(Integer carId, Integer accountId) {
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