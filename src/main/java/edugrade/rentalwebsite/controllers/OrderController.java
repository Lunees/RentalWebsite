package edugrade.rentalwebsite.controllers;

import edugrade.rentalwebsite.dtos.RentalOrderDTO;
import edugrade.rentalwebsite.dtos.RentalOrderIdDTO;
import edugrade.rentalwebsite.entities.RentalOrder;
import edugrade.rentalwebsite.entities.RentalOrderId;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.RentalOrderRepository;


import edugrade.rentalwebsite.services.RentalOrderService;
import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;


import java.security.Principal;


@Controller
public class OrderController {



    @Autowired
    CarRepository carRepository;
    @Autowired
    RentalOrderRepository rentalOrderRepository;

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
    public String registerBooking(@ModelAttribute RentalOrderId rentalOrderId){

        System.out.println(rentalOrderId.getCarId());
        rentalOrderService.SaveOrder(rentalOrderId);
        return "redirect:/list-cars";
    }

    @PostMapping("/delete-booking")
    public String deleteBooking(@RequestParam RentalOrder rentalOrderId){
        rentalOrderRepository.delete(rentalOrderId);
        logger.info("Removing order " + rentalOrderId);
        return "redirect:/orders/customer-id";
    }


    @GetMapping("/orders/customer-id")
    public ModelAndView OrderListModel(){
        ModelAndView modelAndView = new ModelAndView("/OrderList");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object customerId = auth.getPrincipal();


        return modelAndView;
    }

    @GetMapping("/update/booking-id")
    public ModelAndView OrderListUpdateModel(@RequestParam RentalOrder rentalOrder){
        ModelAndView modelAndView = new ModelAndView("/UpdateBooking");




        return modelAndView;
    }







}
