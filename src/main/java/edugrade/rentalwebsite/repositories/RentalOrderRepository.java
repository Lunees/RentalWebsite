package edugrade.rentalwebsite.repositories;

import edugrade.rentalwebsite.entities.RentalOrder;
import edugrade.rentalwebsite.entities.RentalOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalOrderRepository extends JpaRepository<RentalOrder, RentalOrderId> {


    List<RentalOrder> findByRentalOrderId(RentalOrder rentalOrderId);

    public List<RentalOrder> findByUserAccountAccountId(Integer accountId);

    public List<RentalOrder> findByCarCarId(Integer accountId);
}
