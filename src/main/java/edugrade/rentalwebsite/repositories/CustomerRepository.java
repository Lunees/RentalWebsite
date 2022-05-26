package edugrade.rentalwebsite.repositories;

import edugrade.rentalwebsite.entities.Customer;
import edugrade.rentalwebsite.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByUserAccount(UserAccount userAccount);
}
