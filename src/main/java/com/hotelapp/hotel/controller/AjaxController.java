package com.hotelapp.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import com.hotelapp.hotel.pojo.AjaxBookingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.service.BookingService;
import com.hotelapp.hotel.service.UserService;

import com.hotelapp.hotel.pojo.AjaxResponseBody;

@RestController
public class AjaxController {
	
	@Autowired 
	private BookingService bookingService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/index/makeBooking", method = RequestMethod.POST)
	public ResponseEntity<?> makeBooking(HttpServletRequest request, @RequestBody AjaxBookingInfo bookInfo){

		User user = userService.getUserSessionInfo(request);

		Booking booking = new Booking(bookInfo.getHotelName(), bookInfo.getHotelId(), user);
		bookingService.makeBooking(booking);

		AjaxResponseBody result = new AjaxResponseBody();
		result.setMsg("Booking made!");

		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/bookings/delete/{hotelId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBooking(HttpServletRequest request, @PathVariable("hotelId") String hotelId) {
		
		User user = userService.getUserSessionInfo(request);
		
		Booking booking = bookingService.findBooking(user, hotelId);
		
		bookingService.deleteBooking(booking);	
		
		AjaxResponseBody result = new AjaxResponseBody();
		result.setMsg("Booking deleted");
		
		return ResponseEntity.ok(result);

	}
}
