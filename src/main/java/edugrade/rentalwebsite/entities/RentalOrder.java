package edugrade.rentalwebsite.entities;

import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
public class RentalOrder  {
    @EmbeddedId
    private RentalOrderId id;

    @Column(name = "orderId", columnDefinition = "bigint not null auto_increment")
    private Long orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public RentalOrder() {
    }

    public RentalOrder(RentalOrderId id, Long orderId, Date dateFrom, Date dateTo) {
        this.id = id;
        this.orderId = orderId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public RentalOrderId getId() {
        return id;
    }

    public void setId(RentalOrderId rentalOrderId) {
        this.id = rentalOrderId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RentalOrder that = (RentalOrder) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

