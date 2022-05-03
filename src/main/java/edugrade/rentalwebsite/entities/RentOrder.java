package edugrade.rentalwebsite.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RentOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "car_car_id", nullable = false)
    private Car car;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_account_account_id", nullable = false)
    private UserAccount userAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}