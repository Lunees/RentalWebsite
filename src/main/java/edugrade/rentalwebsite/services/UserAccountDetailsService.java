package edugrade.rentalwebsite.services;

import edugrade.rentalwebsite.controllers.AdminController;
import edugrade.rentalwebsite.entities.Customer;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.entities.Role;
import edugrade.rentalwebsite.repositories.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




@Service @Transactional
public class UserAccountDetailsService implements UserAccountService, UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    public UserAccountDetailsService(UserAccountRepository userAccountRepository, CustomerRepository customerRepository, RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) {
            logger.info("Username not found");
            throw new UsernameNotFoundException("Username could not be found");
        } else {
            logger.info("User found in the database:" + userAccountRepository.findByUsername(username));
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userAccount.getRoles().forEach(role ->
            authorities.add(new SimpleGrantedAuthority(role.getName())));


        return userAccount;
       // return new CustomUserAccountDetails(userAccount.getUsername(), userAccount.getPassword(),authorities);
        }

    @Override
    public UserAccount saveUser(UserAccount user) {
        logger.info("Saving new user " + userAccountRepository.findByUsername(user.getUsername()) + " to the database");
        return userAccountRepository.save(user);
    }
    @Override
    public void saveCustomer(UserAccount userAccount, Customer customer) {
        logger.info("Saving new Customer account " + userAccountRepository.findByUsername(userAccount.getUsername()));
        userAccountRepository.save(userAccount);
        userAccount.setUserAccount(customer);
        customerRepository.save(customer);


    }

    @Override
    public Role saveRole(Role role) {
        logger.info("Saving new role" + roleRepository.findByName(role.getName()) + "to the database");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUserAccount(String username, String roleName) {
        logger.info("Adding the role " + roleRepository.findByName(roleName) + " to user" + userAccountRepository.findByUsername(username));
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        userAccount.getRoles().add(role);
    }

    @Override
    public UserAccount getUser(String username) {
        logger.info("Fetching user " + userAccountRepository.findByUsername(username));
        return userAccountRepository.findByUsername(username);
    }

    @Override
    public List<UserAccount> getUsers() {
        logger.info("Fetching all users");
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount getCustomer(Integer customer) {
        logger.info("Fetching customer");
        return userAccountRepository.findByCustomer(customer);
    }

}
