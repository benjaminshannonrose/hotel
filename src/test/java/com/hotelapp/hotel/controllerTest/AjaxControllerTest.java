package com.hotelapp.hotel.unitTest.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelapp.hotel.controller.AjaxController;
import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.pojo.AjaxBookingInfo;
import com.hotelapp.hotel.service.BookingService;
import com.hotelapp.hotel.service.UserService;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AjaxController.class)
public class AjaxControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private BookingService bookingService;
    @MockBean
    private User user;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        Mockito.when(userService.findUser("makebookingajax","123456789")).thenReturn(user);
    }

    @Test
    public void makeBookingTest() throws Exception{
        AjaxBookingInfo info = new AjaxBookingInfo();
        info.setHotelName("FancyHotelname");
        info.setHotelId("FancyHotelId");

        mockMvc.perform(post("/index/makeBooking")
                .sessionAttr("username", "makebookingajax")
                .sessionAttr("password", "123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(info)))
        .andExpect(status().isOk());
        Mockito.verify(bookingService).makeBooking(any(Booking.class));
    }

    @Test
    public void deleteBookingTest() throws Exception{
        String jsonHotelId = "{'hotelId' : 'FancyHotelId'}";

        mockMvc.perform(delete("/bookings/delete/{hotelId}", jsonHotelId)
                .sessionAttr("username", "deletebookingajax")
                .sessionAttr("password", "123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(bookingService).deleteBooking(any(Booking.class));
    }

}
