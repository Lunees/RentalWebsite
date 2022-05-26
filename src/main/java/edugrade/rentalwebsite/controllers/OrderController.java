package edugrade.rentalwebsite.controllers;

import edugrade.rentalwebsite.entities.RentalOrder;
import edugrade.rentalwebsite.entities.RentalOrderId;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.RentalOrderRepository;


import edugrade.rentalwebsite.repositories.UserAccountRepository;
import edugrade.rentalwebsite.services.RentalOrderService;
import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;


import java.security.Principal;
import java.util.List;



@Controller
public class OrderController {



    @Autowired
    CarRepository carRepository;
    @Autowired
    RentalOrderRepository rentalOrderRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserAccountDetailsService userAccountDetailsService;

    @Autowired
    RentalOrderService rentalOrderService;


    private static final Logger logger = Logger.getLogger(OrderController.class);

    @GetMapping("/bookingform")
    public ModelAndView OrderModel(Principal principal,@ModelAttribute RentalOrderId rentalOrderId) {
        return rentalOrderService.bookingOrder(principal,rentalOrderId);

    }

    @PostMapping("/bookingform/save")
    public String registerBooking(@ModelAttribute RentalOrder rentalOrder){
        rentalOrderRepository.save(rentalOrder);
        logger.info("Creating a new order");
        return "redirect:/orders/customer-id";
    }




    @GetMapping("/orders/customer-id")
    public ModelAndView OrderListModel(Principal principal){
        ModelAndView modelAndView = new ModelAndView("/OrderList");
        String userName = principal.getName();
        UserAccount userAccount = userAccountRepository.findByUsername(userName);
        List<RentalOrder> rentalOrderList = rentalOrderRepository.findByIdAccountId(userAccount);
        modelAndView.addObject("rentalOrderList",rentalOrderList);
        logger.info("Fetching list of orders for customer");
        return modelAndView;
    }

    @GetMapping("/delete-booking")
    public String deleteBooking(@RequestParam(value="id")Long id){
        RentalOrder rentalOrder = rentalOrderRepository.findRentalOrderByOrderId(id);
        rentalOrderRepository.delete(rentalOrder);
        logger.info("Removing order");
        return "redirect:/orders/customer-id";
    }

    @GetMapping("/update/booking-id")
    public ModelAndView orderUpdate(Principal principal,@RequestParam(value = "id")Long id){
        ModelAndView modelAndView = new ModelAndView("/booking");
        RentalOrder rentalOrder = rentalOrderRepository.getRentalOrderByOrderId(id);
        modelAndView.addObject("rentalOrder",rentalOrder);
        return rentalOrderService.updateOrder(principal,id);
    }
}
