package edugrade.rentalwebsite;

import edugrade.rentalwebsite.entities.Customer;
import edugrade.rentalwebsite.entities.Role;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.CustomerRepository;
import edugrade.rentalwebsite.services.UserAccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import java.util.ArrayList;

@SpringBootApplication
public class RentalWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalWebsiteApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


   /* @Bean
    CommandLineRunner run(UserAccountService userAccountService){
        return args -> {


            userAccountService.saveRole(new Role(null,"ROLE_ADMIN"));

            userAccountService.saveUser(new UserAccount(null,"tomas", bCryptPasswordEncoder().encode("tomas"),new ArrayList<>(),null,null));

            userAccountService.addRoleToUserAccount("tomas","ROLE_ADMIN");

        };
    } */
}

