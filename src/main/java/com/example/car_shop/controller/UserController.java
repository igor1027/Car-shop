package com.example.car_shop.controller;

import javax.validation.Valid;

import com.example.car_shop.entity.User;
import com.example.car_shop.repository.UserRepository;
import com.example.car_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home(){
//        return "header";
//    }



    @RequestMapping(value = "registration", method = RequestMethod.GET)
//    @GetMapping(value = "/registration")
    public ModelAndView registration(ModelAndView modelAndView, User user){
        modelAndView.addObject("newUser", user);
        modelAndView.setViewName("registration");
        return modelAndView;

    }

//    @GetMapping(value = "/registration")
//    public String registration(Model model){
//        model.addAttribute("newUser", new User());
//        return "registration";
//    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
//    @PostMapping("/registration")
    public ModelAndView saveNewUser(@Valid @RequestBody User user, ModelAndView modelAndView, BindingResult bindingResult){
       Optional<User> existUser = userService.findUserByEmail(user.getEmail());
       Optional<User> existPhone = userService.findUserByPhoneNumber(user.getPhone());
       if(existUser != null){
       }
       if(bindingResult.hasErrors()){
           modelAndView.setViewName("registration");
       }else{
           userService.save(user);
           modelAndView.addObject("newUser", new User());
           modelAndView.setViewName("registration");
       }
        return modelAndView;
    }

//    @PostMapping(value = "/registration")
//    public String registration(@ModelAttribute("newUser") User user, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            return "registration";
//        }else{
//            userService.save(user);
////           modelAndView.addObject("user", new User());
////           model.addAttribute("user", new User());
//            return "registration";
//        }
//    }
}
