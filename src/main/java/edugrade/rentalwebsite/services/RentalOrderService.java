package edugrade.rentalwebsite.services;

import edugrade.rentalwebsite.dtos.RentalOrderIdDTO;
import edugrade.rentalwebsite.entities.*;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.RentalOrderRepository;
import edugrade.rentalwebsite.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.List;

@Service
public class RentalOrderService {
    @Autowired
    DataSource dataSource;

     @Autowired
    RentalOrderRepository rentalOrderRepository;
    @Autowired
     UserAccountRepository userAccountRepository;
    @Autowired
     CarRepository carRepository;








     public ModelAndView bookingOrder(Principal principal,@ModelAttribute RentalOrderId rentalOrderId){
         String userName = principal.getName();
         ModelAndView modelAndView = new ModelAndView("/Booking");

         UserAccount userAccount = userAccountRepository.findByUsername(userName);


         List<Car> car = carRepository.findAll();

         modelAndView.addObject("car", car);
         modelAndView.addObject("userAccount", userAccount);
         modelAndView.addObject("rentalOrderId", rentalOrderId);
         System.out.println(userAccount);
         return modelAndView;
     }

     public void SaveOrder(@ModelAttribute RentalOrderId rentalOrderId){

         RentalOrder rentalOrder = new RentalOrder();
         rentalOrder.setId(rentalOrderId);
         rentalOrder.setDateFrom();
         rentalOrder.setDateTo();
         rentalOrderRepository.save(rentalOrder);

     }

}
