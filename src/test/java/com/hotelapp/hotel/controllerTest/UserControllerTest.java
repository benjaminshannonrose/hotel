package com.hotelapp.hotel.unitTest.controllerTest;

import com.hotelapp.hotel.controller.UserController;
import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.service.BookingService;
import com.hotelapp.hotel.service.UserService;
import com.hotelapp.hotel.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;


import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @TestConfiguration
    static class UserControllerTestConfig{
        @Bean
        public UserValidator userValidator(){
            return new UserValidator();
        }
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserValidator userValidator;
    @MockBean
    private UserService userService;
    @MockBean
    private BindingResult bindingResult;
    @MockBean
    private ModelMap model;
    @MockBean
    private BookingService bookingService;


    @Before
    public void setUp() {
        Mockito.when(userService.findUser("realname", "123456789")).thenReturn(new User("realname", "123456789"));
        Mockito.when(userService.findUser("suchanawfulname", "sketchypassword")).thenReturn(null);
    }

    @Test
    public void indexGetTest() throws  Exception{
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("/index"));
    }

    @Test
    public void registrationGetTest() throws Exception{
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("/signup"));
    }

    @Test
    public void registrationPostHappyPathTest() throws Exception {
        mockMvc.perform(post("/signup")
                .param("username", "testusername")
                .param("password", "testpassword")
                .param("passwordConfirm", "testpassword")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void registrationPostSadPathTest() throws Exception{

        mockMvc.perform(post("/signup")
                .param("username", "short")
                .param("password", "short")
                .param("passwordConfirm", "nonmatchingpassword")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("/signup"));
    }

    @Test
    public void loginGetTest() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    public void loginPostHappyPathTest() throws Exception{

        mockMvc.perform(post("/login")
                .param("username", "realname")
                .param("password","123456789")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    public void loginPostSadPathTest() throws Exception{

        mockMvc.perform(post("/login")
                .param("username", "suchanawfulname")
                .param("password","sketchypassword")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    public void logOutTest() throws Exception{

        mockMvc.perform(get("/logout")
                .sessionAttr("username","realname")
                .sessionAttr("password", "123456789"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void bookErrorTest() throws Exception{

        mockMvc.perform(post("/bookError"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void bookingsLoggedInTest() throws Exception{

        mockMvc.perform(get("/bookings")
                .sessionAttr("username","realname")
                .sessionAttr("password", "123456789"))
                .andExpect(status().isOk())
                .andExpect(view().name("/bookings"));
        Mockito.verify(userService).getUserSessionInfo(any(MockHttpServletRequest.class));
        Mockito.verify(bookingService).findAllBookings(any(User.class));
    }

    @Test
    public void bookingsNotLoggedInTest() throws Exception{
        mockMvc.perform(get("/bookings"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));
    }
}
