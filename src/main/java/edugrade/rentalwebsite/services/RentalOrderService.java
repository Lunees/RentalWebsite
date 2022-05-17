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



     public Iterable<RentalOrder> getAll(){
         return rentalOrderRepository.findAll();
     }
     public List<RentalOrder> getByUserAccount(Integer accountId){
         return rentalOrderRepository.findByUserAccountAccountId(accountId);
     }

     public List<RentalOrder> getByCarId(Integer carId){
         return rentalOrderRepository.findByCarCarId(carId);
     }

     public Optional<RentalOrder> getByRentalOrderId(RentalOrderId rentalOrderId){
         return rentalOrderRepository.findById(rentalOrderId);
     }

     public ModelAndView bookingOrder(Principal principal){
         String userName = principal.getName();
         ModelAndView modelAndView = new ModelAndView("/Booking");

         UserAccount userAccount = userAccountRepository.findByUsername(userName);
         RentOrderWrapper rentOrderWrapper = new RentOrderWrapper();






         List<Car> carOptions = carRepository.findAll();

         modelAndView.addObject("carOptions", carOptions);
         modelAndView.addObject("customerId", userAccount);
         modelAndView.addObject("rentOrderWrapper", rentOrderWrapper);

         return modelAndView;
     }

     public RentalOrder save ( Integer carId, Integer accountId){

         RentalOrder rentalOrder = new RentalOrder();
         rentalOrder.setRentalOrderId(new RentalOrderId(carId,accountId));
         return rentalOrderRepository.save(rentalOrder);
     }
}
