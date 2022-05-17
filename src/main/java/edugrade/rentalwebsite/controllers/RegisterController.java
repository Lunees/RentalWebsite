package edugrade.rentalwebsite.controllers;


import edugrade.rentalwebsite.entities.Customer;

import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.RoleRepository;
import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;




@RestController
public class RegisterController {

    @Autowired
    private UserAccountDetailsService userAccountDetailsService;


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
        userAccountDetailsService.saveCustomer(userAccount, customer);
        userAccountDetailsService.addRoleToUserAccount(String.valueOf(userAccount),"CUSTOMER");
        return "redirect:/register";
    }




}
