package edugrade.rentalwebsite.controllers;

import edugrade.rentalwebsite.entities.Car;
import edugrade.rentalwebsite.entities.UserAccount;
import edugrade.rentalwebsite.repositories.CarRepository;
import edugrade.rentalwebsite.repositories.UserAccountRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class IndexController {

    UserAccountRepository userAccountRepository;

    CarRepository carRepository;

    public IndexController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "index")
    String getHomepage() {
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public String
    currentUserName(SecurityContextHolder securityContextHolder){
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return currentUserName(securityContextHolder);
    }

    @GetMapping("/template")
    public ModelAndView fetchTemplate() {
        ModelAndView modelAndView = new ModelAndView("/Template");
        List<Car> cars = carRepository.findAll();
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }
}