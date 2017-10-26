package com.hotelapp.hotel.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
	
    public void saveUser(User user) {
    	userRepository.save(user);
    }

	public User findUser(String username, String password) {
		
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}

	public User verifyUsernameNotTaken(String username) {
    	User user = userRepository.findByUsername(username);
    	return user;
	}
	
	public User getUserSessionInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		String password = session.getAttribute("password").toString();
		return findUser(username, password);
	}
}
