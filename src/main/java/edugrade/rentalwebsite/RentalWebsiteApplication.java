package edugrade.rentalwebsite;

import edugrade.rentalwebsite.entities.Role;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    @Bean
    CommandLineRunner run(UserAccountService userAccountService){
        return args -> {
            userAccountService.saveRole(new Role(null,"ROLE_USER"));

            userAccountService.saveUser(new UserAccount(null,"john","john",new ArrayList<>()));

            userAccountService.addRoleToUserAccount("john","ROLE_USER");
        };
    }
}

