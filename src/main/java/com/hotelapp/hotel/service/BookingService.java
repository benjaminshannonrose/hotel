package com.hotelapp.hotel.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public void makeBooking(Booking booking) {
		bookingRepository.save(booking);
	}
	
	public List<Booking> findAllBookings (User user){
		List<Booking> bookings = bookingRepository.findByUser(user);
		return bookings;
	}
	
	public Booking findBooking(User user, String hotelId) {
		return bookingRepository.findByUserAndHotelId(user, hotelId);
	}
	
	public void deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
	}
	
}
