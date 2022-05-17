package edugrade.rentalwebsite.controllers;

import edugrade.rentalwebsite.entities.Car;
import edugrade.rentalwebsite.entities.RentalOrder;
import edugrade.rentalwebsite.entities.RentalOrderId;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.RentalOrderRepository;

import edugrade.rentalwebsite.entities.RentOrderWrapper;
import edugrade.rentalwebsite.services.RentalOrderService;
import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    UserAccountDetailsService userAccountDetailsService;

    @Autowired
    RentalOrderService rentalOrderService;


    private static final Logger logger = Logger.getLogger(OrderController.class);

    @GetMapping("/bookingform")
    public ModelAndView OrderModel(Principal principal) {
        return rentalOrderService.bookingOrder(principal);
    }

    @PostMapping("/bookingform/save")
    @ResponseBody
    public String registerBooking(@ModelAttribute RentOrderWrapper rentOrderWrapper){
        RentalOrder rentalOrder2 = rentalOrderService.save(rentOrderWrapper.getAccountId(), rentOrderWrapper.getCarId());
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
        List<RentalOrder> rentalOrders = rentalOrderRepository.findAllById((Iterable<RentalOrderId>) auth);

        modelAndView.addObject(rentalOrders);
        return modelAndView;
    }

    @GetMapping("/update/booking-id")
    public ModelAndView OrderListUpdateModel(@RequestParam RentalOrder rentalOrder){
        ModelAndView modelAndView = new ModelAndView("/UpdateBooking");

        List<RentalOrder> rentalOrders = rentalOrderRepository.findByRentalOrderId(rentalOrder);
        modelAndView.addObject("rentalOrder", rentalOrder);

        List<Car> carOptions = carRepository.findAll();
        modelAndView.addObject("carOptions", carOptions);


        return modelAndView;
    }







}
