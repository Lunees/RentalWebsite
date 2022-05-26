package edugrade.rentalwebsite.repositories;

import edugrade.rentalwebsite.entities.RentalOrder;
import edugrade.rentalwebsite.entities.RentalOrderId;
import edugrade.rentalwebsite.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalOrderRepository extends JpaRepository<RentalOrder, RentalOrderId> {

    List<RentalOrder> findByIdAccountId(UserAccount userAccount);
    RentalOrder getRentalOrderByOrderId(Long id);

    RentalOrder findRentalOrderByOrderId(Long id);
}
