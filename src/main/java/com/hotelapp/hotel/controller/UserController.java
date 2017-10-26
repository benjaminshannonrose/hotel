package com.hotelapp.hotel.controller;

import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.service.BookingService;
import com.hotelapp.hotel.service.UserService;
import com.hotelapp.hotel.validator.UserValidator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserValidator userValidator;
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
    	model.addAttribute("bookingForm", new Booking());
    	return "index";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(ModelMap model) {
    	model.addAttribute("userForm", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult result, ModelMap model) {

        userValidator.validate(userForm, result);

        if (result.hasErrors()){
            return "signup";
        }

        userService.saveUser(userForm);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        model.addAttribute("loginForm", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @ModelAttribute("loginForm") User loginForm, ModelMap model) {
    	
    	User user = userService.findUser(loginForm.getUsername(), loginForm.getPassword());
    	if(user != null) {
    	HttpSession session = request.getSession(true);
    	session.setAttribute("username", user.getUsername());
    	session.setAttribute("password", user.getPassword());
    	System.out.println(session.getAttribute("username").toString());
    	return "redirect:/index";
    	}

    	else {
    		model.addAttribute("error", "Invalid login credentials.");
            return "/login";
        }
  
    }
        
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {

    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "redirect:/login";
    }
    
    @RequestMapping(value = "/bookError", method = RequestMethod.POST)
    public String bookError(ModelMap model) {
    		return "redirect:/login";
    	}


    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public String bookings(HttpServletRequest request, ModelMap model) {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("username") == null){
    		return "redirect:/login";
    	}
    	else {
    		User user = userService.getUserSessionInfo(request);
        	List<Booking> bookingList = bookingService.findAllBookings(user);
        	model.addAttribute("bookingList", bookingList);        	
        	return "/bookings";
    	}
    }

} 
