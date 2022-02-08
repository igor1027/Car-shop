package com.example.car_shop.controller;


import com.example.car_shop.entity.Car;
import com.example.car_shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Pageable pageable, Model model){
        model.addAttribute("title", "Cars Catalog");
        Page<Car> carsPage = carService.findAllCar(pageable);
//        PageWrapper<Car> page = new PageWrapper<Car>(carsPage, "/");
        model.addAttribute("car", carsPage.getContent());
//        model.addAttribute("page", page);
        return "index";
    }
}
