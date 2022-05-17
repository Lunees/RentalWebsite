package edugrade.rentalwebsite.entities;


import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.EAGER;

@Entity
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountId")
    private Integer accountId;
    private String username;
    private String password;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();



    @OneToOne(mappedBy = "userAccount",optional = true,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalOrder> rentalOrders = new ArrayList<>();

    public List<RentalOrder> getRentalOrders() {
        return rentalOrders;
    }

    public void setRentalOrders(List<RentalOrder> rentalOrders) {
        this.rentalOrders = rentalOrders;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountKey) {
        this.accountId = accountKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public UserAccount(){}

    public UserAccount(Integer accountId, String username, String password, Collection<Role> roles, Customer customer) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAccount that = (UserAccount) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setUserAccount(Customer customer) {
        if (customer == null) {
            if (this.customer != null) {
                this.customer.setCustomerId(null);
            }
        }
        else {
            customer.setUserAccount(this);
        }
        this.customer = customer;
    }



}
