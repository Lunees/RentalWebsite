package edugrade.rentalwebsite.controllers;

import edugrade.rentalwebsite.entities.Car;
import edugrade.rentalwebsite.entities.Customer;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.CustomerRepository;
import edugrade.rentalwebsite.repositories.UserAccountRepository;
import edugrade.rentalwebsite.services.UserAccountDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller

public class AdminController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserAccountDetailsService userAccountDetailsService;
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final Logger logger = Logger.getLogger(AdminController.class);


    @GetMapping("/admin/customers")
    public ModelAndView CustomerModel(){
        ModelAndView modelAndView = new ModelAndView("/AdminListCustomers");
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        modelAndView.addObject("userAccounts",userAccounts);
        return modelAndView;
    }

    @GetMapping("/admin/customers/customer-id")
    public ModelAndView updateCustomer(@RequestParam(value="username") String username){
        ModelAndView modelAndView = new ModelAndView("/AdminUpdateCustomer");
        UserAccount account = userAccountRepository.findUserAccountByUsername(username);
        Customer customer = customerRepository.findCustomerByUserAccount(account);
        modelAndView.addObject("account",account);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/admin/save-customer")
    public String registerAccount(@ModelAttribute ("customer") Customer customer, @ModelAttribute ("account") UserAccount userAccount){

        userAccount.setPassword(bCryptPasswordEncoder().encode(userAccount.getPassword()));

        userAccountDetailsService.saveCustomer(userAccount, customer);


        return "redirect:/admin/customers";
    }

    @GetMapping("/admin/delete-customer")
    public String deleteCustomer(@RequestParam(value = "id") Long accountId){
        userAccountRepository.deleteById(accountId);
        return "redirect:/admin/customers";
    }

    @GetMapping("/admin/vehicles")
    public ModelAndView AdminCarList() {
        ModelAndView modelAndView = new ModelAndView("/AdminListCars");
        List<Car> cars = carRepository.findAll();
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("admin/add-vehicle")
    public ModelAndView AddVehicle(){
        ModelAndView modelAndView = new ModelAndView("/AdminAddCar");
        Car newCar = new Car();
        modelAndView.addObject("newCar", newCar);
        return modelAndView;
    }

    @PostMapping("admin/save-car")
    public String saveCar(@ModelAttribute Car car){
            carRepository.save(car);
            logger.info("Saved Car" + car.getCarModel() + "to id " + car.getCarId());
            return "redirect:/admin/vehicles";
        }


    @GetMapping("admin/vehicle/vehicle-id")
    public ModelAndView updateVehicle(@RequestParam(value = "carId") Long carId){
        ModelAndView modelAndView = new ModelAndView("/AdminAddCar");
        Optional<Car> newCar = carRepository.findById(carId);
        modelAndView.addObject("newCar",newCar);
        return modelAndView;
    }

    @GetMapping("admin/delete-vehicle")
    public String deleteCar(@RequestParam Long carId){
        carRepository.deleteById(carId);
        logger.info("car deleted: " + carId);
        return "redirect:/admin/vehicles";
    }
}
