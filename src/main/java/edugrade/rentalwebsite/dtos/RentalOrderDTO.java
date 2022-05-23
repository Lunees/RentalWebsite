package edugrade.rentalwebsite.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import edugrade.rentalwebsite.entities.RentalOrder;
import edugrade.rentalwebsite.entities.RentalOrderId;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalOrderDTO {

    private RentalOrderIdDTO rentalOrderIdDTO;
    private RentalOrderId rentalOrderId;

    public RentalOrderIdDTO getRentalOrderIdDTO() {
        return rentalOrderIdDTO;
    }

    public void setRentalOrderIdDTO(RentalOrderIdDTO rentalOrderIdDTO) {
        this.rentalOrderIdDTO = rentalOrderIdDTO;
    }

    public RentalOrderDTO(RentalOrderIdDTO rentalOrderIdDTO) {
        this.rentalOrderIdDTO = rentalOrderIdDTO;
    }

    public RentalOrder toEntity(RentalOrderIdDTO rentalOrderIdDTO){
        RentalOrder rentalOrder = new RentalOrder();

        rentalOrder.setId(this.rentalOrderId);
        return rentalOrder;
    }
}
