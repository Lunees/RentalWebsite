package edugrade.rentalwebsite.controllers;


import edugrade.rentalwebsite.entities.Customer;

import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.UserAccountRepository;
import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class RegisterController {

    @Autowired
    private UserAccountDetailsService userAccountDetailsService;
    @Autowired
    UserAccountRepository userAccountRepository;

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("/Register");
        UserAccount userAccount = new UserAccount();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("account", userAccount);
        return modelAndView;
    }

    @PostMapping("/register/save")
    public String registerAccount(@ModelAttribute ("customer") Customer customer, @ModelAttribute ("account") UserAccount userAccount){
        userAccount.setPassword(bCryptPasswordEncoder().encode(userAccount.getPassword()));
        String username = userAccount.getUsername();
        userAccountDetailsService.saveCustomer(userAccount, customer);
        userAccountDetailsService.addRoleToUserAccount(username,"ROLE_CUSTOMER");
        return "redirect:/index";
    }
}
