package edugrade.rentalwebsite.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edugrade.rentalwebsite.entities.RentalOrderId;
import org.springframework.lang.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalOrderIdDTO {


    public Long carId;


    public Long accountId;

    @NonNull
    public Long getCarId() {
        return carId;
    }

    public void setCarId( Long carId) {
        this.carId = carId;
    }

    @NonNull
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public RentalOrderIdDTO() {
    }

    public RentalOrderIdDTO(RentalOrderId rentalOrderId) {
        this.carId = rentalOrderId.getCarId().getCarId();
        this.accountId = rentalOrderId.getAccountId().getAccountId();
    }
}