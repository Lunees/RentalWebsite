package edugrade.rentalwebsite.entities;

import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
public class RentalOrder  {
    @EmbeddedId
    private RentalOrderId Id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public RentalOrder() {
    }

    public RentalOrder(RentalOrderId id, Date dateFrom, Date dateTo) {
        Id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public RentalOrderId getId() {
        return Id;
    }

    public void setId(RentalOrderId rentalOrderId) {
        this.Id = rentalOrderId;
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
        return Id != null && Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }


}

