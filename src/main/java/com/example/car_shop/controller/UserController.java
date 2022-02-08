package com.example.car_shop.controller;

import com.example.car_shop.controller.exception.DataNotFoundControllerException;
import com.example.car_shop.entity.User;
import com.example.car_shop.service.CarService;
import com.example.car_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @GetMapping("/profile")
    public ModelAndView profile(@CurrentSecurityContext(expression = "authentication") Authentication authentication) throws DataNotFoundControllerException {
        User user = (User) authentication.getPrincipal();
        User findUser = userService.findById(user.getId()).orElseThrow(DataNotFoundControllerException::new);
        return new ModelAndView("userProfile", "user", findUser);
    }

    @GetMapping("/view_my_car")
    public ModelAndView viewAllUserAds(@CurrentSecurityContext(expression = "authentication") Authentication authentication) throws DataNotFoundControllerException{
        User user = (User) authentication.getPrincipal();
        return new  ModelAndView("viewCarUser", "car", carService.findByIdAndCreator(user));
    }

}
