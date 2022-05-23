package edugrade.rentalwebsite.controllers;

import edugrade.rentalwebsite.entities.Car;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.UserAccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    private static final Logger logger = Logger.getLogger(AdminController.class);


    @GetMapping("/customers")
    public ModelAndView CustomerModel(){
        ModelAndView modelAndView = new ModelAndView("/AdminListCustomers");
        List<UserAccount> customers = userAccountRepository.findAll();
        modelAndView.addObject("customers",customers);

        return modelAndView;
    }

    @GetMapping("/customers/customer-id")
    public ModelAndView updateCustomer(@RequestParam Integer accountId){
        ModelAndView modelAndView = new ModelAndView("/AdminUpdateCustomer");
        UserAccount customer = userAccountRepository.findById(Long.valueOf(String.valueOf(accountId))).get();
        modelAndView.addObject(customer);
        return modelAndView;
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@ModelAttribute UserAccount customer){
        try {
            userAccountRepository.save(customer);
            logger.info("Customer Updated: " + customer.getCustomer().getFirstName() + ", " + customer.getAccountId());
        }catch (Exception e){
            logger.fatal("Could not update customer", e);}
        finally{
            return "redirect:/customers";
        }
    }

    @GetMapping("/delete-customer")
    public String deleteCustomer(@RequestParam Integer userAccountId){
        userAccountRepository.deleteById(Long.valueOf(userAccountId));
        return "redirect:/admin/customers";
    }

    @GetMapping("/vehicles")
    public ModelAndView AdminCarModel() {

        ModelAndView modelAndView = new ModelAndView("/AdminListCars");

        List<Car> cars = carRepository.findAll();

        modelAndView.addObject("cars", cars);

        return modelAndView;
    }

    @GetMapping("/add-vehicle")
    public ModelAndView AddVehicleModel(){
        ModelAndView modelAndView = new ModelAndView("/AdminAddCar");
        Car newCar = new Car();
        modelAndView.addObject("newCar", newCar);
        return modelAndView;
    }

    @PostMapping("/save-car")
    public String saveCar(@ModelAttribute Car car){
        try{
            carRepository.save(car);
            logger.info("Saved Car" + car.getCarModel() + "to id " + car.getCarId());
        }catch(Exception e){
            logger.fatal("car could not be created");
        }finally{
            return "redirect:/Admin/vehicles";
        }
    }

    @GetMapping("/vehicle/vehicle-id")
    public ModelAndView updateVehicle(@RequestParam Integer carId){
        ModelAndView modelAndView = new ModelAndView("/AdminAddCar");
        Car car = carRepository.findById(Long.valueOf(carId)).get();
        modelAndView.addObject(car);
        return modelAndView;
    }

    @GetMapping("/delete-vehicle")
    public String deleteCar(@RequestParam Integer carId){
        carRepository.deleteById(Long.valueOf(carId));
        logger.info("car deleted: " + carId);
        return "redirect:/Admin/vehicles";
    }

}
