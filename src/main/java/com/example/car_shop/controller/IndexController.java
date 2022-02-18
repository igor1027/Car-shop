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

import java.util.List;

@Controller
public class IndexController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Pageable pageable, Model model){
//        model.addAttribute("title", "Cars Catalog");
//        Page<Car> carsPage = carService.findAllCar(pageable);
//        model.addAttribute("car", carsPage.getContent());

//        return "index";
        return findPaginated(1, "price", "asc", model);
    }

    @RequestMapping(value = "/{mark}/page/{pageNo}", method = RequestMethod.GET)
    public String sortedMark(@PathVariable String mark, @PathVariable (value = "pageNo") int pageNo,
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDir") String sortDir, Pageable pageable, Model model){
//        ModelAndView modelAndView = new ModelAndView();
//        Page<Car> cars = carService.findAllByMark(mark, pageable);
//        model.addAttribute("car", cars.getContent());
//        modelAndView("")
//        modelAndView.setViewName("index");
//
//        findPaginated(1, "price", "asc", model);
//        return modelAndView;

        int pageSize = 5;

        Page<Car> page = carService.findPaginatedSortedCar(mark,pageNo, pageSize, sortField, sortDir);
        List<Car> carList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("car", carList);
        return "index";
    }


    @RequestMapping(value = "/page/{pageNo}", method = RequestMethod.GET)
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;

        Page<Car> page = carService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Car> carList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("car", carList);
        return "index";
    }


}
