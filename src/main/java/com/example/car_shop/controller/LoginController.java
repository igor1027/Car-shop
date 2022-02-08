package com.example.car_shop.controller;

import javax.validation.Valid;

import com.example.car_shop.dto.UserRegistrationDto;
import com.example.car_shop.entity.User;
import com.example.car_shop.repository.UserRepository;
import com.example.car_shop.service.UserService;
import com.example.car_shop.service.exception.EmailIsBusyServiceException;
import com.example.car_shop.service.exception.PhoneIsBusyServiceException;
import com.example.car_shop.service.exception.UsernameIsBusyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RestController
//@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public ModelAndView registration(ModelAndView modelAndView, UserRegistrationDto user){
        modelAndView.addObject("newUser", user);
        modelAndView.setViewName("registration");
        return modelAndView;

    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@Valid @ModelAttribute("newUser") UserRegistrationDto userDto, ModelAndView modelAndView, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return new ModelAndView("registration", bindingResult.getModel());
       }
       try {
           userService.saveUser(userDto);
//           modelAndView.addObject("newUser", new User());
//           modelAndView.setViewName("login");
           return new ModelAndView("redirect:/login");
       }catch (UsernameIsBusyServiceException e) {
           ModelAndView model = new ModelAndView("registration", bindingResult.getModel());
           model.addObject("message", "Username is busy");
           return model;
       } catch (EmailIsBusyServiceException e) {
           ModelAndView model = new ModelAndView("registration", bindingResult.getModel());
           model.addObject("message", "Email is busy");
           return model;
       }catch (PhoneIsBusyServiceException e){
           ModelAndView model = new ModelAndView("registration", bindingResult.getModel());
           model.addObject("message", "Phone is busy");
           return model;
       }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView authorization(ModelAndView modelAndView){
        modelAndView.addObject("authUser", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }



}
