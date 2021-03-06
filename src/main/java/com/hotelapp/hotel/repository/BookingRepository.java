package com.hotelapp.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	List<Booking> findByUser(User user);
	
	Booking findByUserAndHotelId(User user, String hotelId);


}
