package com.hotelapp.hotel.unitTest.serviceTest;

import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.repository.UserRepository;
import com.hotelapp.hotel.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


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

    @Before
    public void setUp(){
        User testUser = new User("testName","testPassword");

        Mockito.when(userRepository.findByUsernameAndPassword(testUser.getUsername(), testUser.getPassword()))
                .thenReturn(testUser);
        Mockito.when(userRepository.save(testUser))
                .thenReturn(null);
    }

    @Test //Don't need @Transactional because of UserRepository @MockBean
    public void saveUserAndFindUserCombinedTest(){

        User savedUser = new User("testName","testPassword");
        userService.saveUser(savedUser);

        String foundUsername = "testName";
        String foundPassword = "testPassword";
        User foundUser = userService.findUser(foundUsername, foundPassword);

        assert(foundUsername).equals(foundUser.getUsername());
        assert(foundPassword).equals(foundUser.getPassword());

    }

}
