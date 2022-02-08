package com.example.car_shop.controller;

import com.example.car_shop.controller.exception.DataNotFoundControllerException;
import com.example.car_shop.dto.CarDto;
import com.example.car_shop.entity.Car;
import com.example.car_shop.entity.User;
import com.example.car_shop.service.CarService;
import com.example.car_shop.service.exception.CarNotFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/create")
    public ModelAndView createCar(@CurrentSecurityContext(expression = "authentication") Authentication authentication,ModelAndView model, Car car){
        User user = (User) authentication.getPrincipal();
        model.addObject("newCar", car);
        model.setViewName("createCar");
        return model;
    }

    @PostMapping("/create")
    public ModelAndView createCar(@CurrentSecurityContext(expression = "authentication") Authentication authentication, @Valid @ModelAttribute("newCar") CarDto carDto, BindingResult result){
        if (result.hasErrors()) {
            return new ModelAndView("boardCar", "newCar", result.getModel());
        }
        User user = (User) authentication.getPrincipal();

        Car car = carService.saveCar(carDto,user);
        return new ModelAndView(String.format("redirect:/"));
    }

    @PostMapping("/{id}/delete")
    public String deleteCar(@PathVariable long id, @CurrentSecurityContext(expression = "authentication") Authentication authentication) throws DataNotFoundControllerException {
        User user = (User) authentication.getPrincipal();
        try {
            Car car = carService.deleteCar(id, user);
            return "redirect:/view_my_car";
        }catch (CarNotFoundServiceException e){
            throw new DataNotFoundControllerException("Car not found");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView viewCar(@PathVariable long id, @CurrentSecurityContext(expression = "authentication") Authentication authentication) throws  DataNotFoundControllerException{
        User user = (User) authentication.getPrincipal();
//        Optional<Car> car = carService.getCarById(id,user);

        Optional<Car> car = carService.id(id);
        if(car.isPresent()){
            ModelAndView modelAndView = new ModelAndView("viewCar", "car", car.get());
            return modelAndView;
        }
        throw new DataNotFoundControllerException();
    }

}
