package edugrade.rentalwebsite.repositories;

import edugrade.rentalwebsite.entities.Customer;
import edugrade.rentalwebsite.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    UserAccount findByUsername(String username);

    UserAccount findByCustomer(Customer customer);

    UserAccount findUserAccountByUsername(String username);


}
