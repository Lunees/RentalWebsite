package edugrade.rentalwebsite.services;


import edugrade.rentalwebsite.entities.Customer;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.entities.Role;

import java.util.List;

public interface UserAccountService {
    UserAccount saveUser(UserAccount user);

    void saveCustomer(UserAccount userAccount, Customer customer);

    Role saveRole(Role role);
    void addRoleToUserAccount(String username, String roleName);
    UserAccount getUser(String username);
    List<UserAccount>getUsers();
    UserAccount getCustomer(Customer customer);



}
