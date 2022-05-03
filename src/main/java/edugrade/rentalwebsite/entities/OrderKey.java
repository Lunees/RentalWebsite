package edugrade.rentalwebsite.entities;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class OrderKey implements Serializable {



    Integer carId;

    Integer accountId;

    public OrderKey() {
    }

    public OrderKey(Integer carId, Integer accountId) {
        this.carId = carId;
        this.accountId = accountId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer car_Ck_Id) {
        this.carId = car_Ck_Id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer account_Ck_Id) {
        this.accountId = account_Ck_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderKey orderKey = (OrderKey) o;
        return carId != null && Objects.equals(carId, orderKey.carId)
                && accountId != null && Objects.equals(accountId, orderKey.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, accountId);
    }
}
