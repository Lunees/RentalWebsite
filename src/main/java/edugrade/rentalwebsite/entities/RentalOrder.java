package edugrade.rentalwebsite.entities;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class RentalOrder {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private RentalOrderId rentalOrderId;



    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "account_Idfk", nullable = false,updatable = false,insertable = false)
    private UserAccount userAccount;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "car_Idfk", nullable = false,updatable = false,insertable = false)
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public RentalOrderId getRentalOrderId() {
        return rentalOrderId;
    }

    public void setRentalOrderId(RentalOrderId rentalOrderId) {
        this.rentalOrderId = rentalOrderId;
    }

    public RentalOrder() {
    }

    public RentalOrder(RentalOrderId rentalOrderId, UserAccount userAccount, Car car) {
        this.rentalOrderId = rentalOrderId;
        this.userAccount = userAccount;
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RentalOrder that = (RentalOrder) o;
        return rentalOrderId != null && Objects.equals(rentalOrderId, that.rentalOrderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalOrderId);
    }
}