package com.hotelapp.hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reservationId;
	private String hotelName;
	private String hotelId;
	@ManyToOne
	@JoinColumn
	private User user;


	public Booking() {};
	
	public Booking(String hotelName, String hotelId, User user) {
		this.hotelName = hotelName;
		this.hotelId = hotelId;
		this.user = user;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
