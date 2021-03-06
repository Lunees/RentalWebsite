package edugrade.rentalwebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import edugrade.rentalwebsite.entities.Car;
import edugrade.rentalwebsite.repositories.CarRepository;


import java.util.List;


@Controller
public class CarsController {

    @Autowired
    CarRepository carRepository;

    public CarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public ModelAndView CarModel() {

       ModelAndView modelAndView = new ModelAndView("/ListCars");

        List<Car> cars = carRepository.findAll();

        modelAndView.addObject("cars", cars);

        return modelAndView;
    }
}