package edugrade.rentalwebsite.services;

import edugrade.rentalwebsite.entities.*;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.RentalOrderRepository;
import edugrade.rentalwebsite.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


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
         RentalOrder rentalOrder = new RentalOrder();
         modelAndView.addObject("car", car);
         modelAndView.addObject("userAccount", userAccount);
         modelAndView.addObject("rentalOrderId", rentalOrderId);
         modelAndView.addObject("rentalOrder",rentalOrder);
         System.out.println(userAccount);
         return modelAndView;
     }

    public ModelAndView updateOrder(Principal principal,@RequestParam(value = "id")Long orderId){
        ModelAndView modelAndView = new ModelAndView("/Booking");
        String userName = principal.getName();
        UserAccount userAccount = userAccountRepository.findByUsername(userName);
        List<Car> car = carRepository.findAll();
        RentalOrder rentalOrder = rentalOrderRepository.getRentalOrderByOrderId(orderId);
        modelAndView.addObject("car", car);
        modelAndView.addObject("rentalOrderId", orderId);
        modelAndView.addObject("rentalOrder",rentalOrder);
        modelAndView.addObject("userAccount", userAccount);
        return modelAndView;
    }

}
