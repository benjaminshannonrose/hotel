package com.hotelapp.hotel.serviceTest;

import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.repository.UserRepository;
import com.hotelapp.hotel.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestConfig{

        @Bean
        public UserService userService(){
            return new UserService();
        }

    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private HttpServletRequest request;

    @Before
    public void setUp(){
        User testUser = new User("testName","testPassword");

        Mockito.when(userRepository.findByUsernameAndPassword(testUser.getUsername(), testUser.getPassword()))
                .thenReturn(testUser);
        Mockito.when(userRepository.findByUsername(testUser.getUsername()))
                .thenReturn(testUser);
    }

    @Test
    public void saveUserTest(){
        User savedUser = new User("testName","testPassword");
        userService.saveUser(savedUser);

        Mockito.verify(userRepository).save(savedUser);
    }

    @Test
    public void findUserTest(){
        String foundUsername = "testName";
        String foundPassword = "testPassword";
        User foundUser = new User(foundUsername,foundPassword);
        userService.findUser(foundUser.getUsername(), foundUser.getPassword());

        Assert.assertEquals("testName",foundUsername);
        Assert.assertEquals("testPassword", foundPassword);
    }

    @Test
    public void verifyUsernameNotTakenTest() {
        String takenUsername = "testName";
        String takenPassword = "testPassword";
        User takenUser = new User(takenUsername, takenPassword);
        userService.verifyUsernameNotTaken(takenUser.getUsername());

        Assert.assertEquals("testName",takenUser.getUsername());
    }
}
