package com.hotelapp.hotel.serviceTest;

import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.repository.BookingRepository;
import com.hotelapp.hotel.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
public class BookingServiceTest {
    private User testUser = new User("testName","testPassword");
    private Booking testBookingOne = new Booking("testHotelName","testHotelId",testUser);
    private Booking testBookingTwo = new Booking("fakeHotelName","fakeHotelId",testUser);

    @TestConfiguration
    static class BookingServiceTestConfig{

        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @Before
    public void setUp(){
        List<Booking> testBookings = Arrays.asList(testBookingOne, testBookingTwo);

        Mockito.when(bookingRepository.findByUserAndHotelId(testUser,testBookingOne.getHotelId()))
                .thenReturn(testBookingOne);
        Mockito.when(bookingRepository.findByUser(testUser))
                .thenReturn(testBookings);


    }

    @Test
     public void makeBookingTest() {
        bookingService.makeBooking(testBookingOne);

        Mockito.verify(bookingRepository).save(testBookingOne);
    }

    @Test
    public void findAllBookingsTest() {
        Integer foundBookingsSize = 2;

        List<Booking> foundBookings = bookingService.findAllBookings(testUser);

        assert(foundBookings.get(0).getHotelName()).equals("testHotelName");
        assert(foundBookings.get(1).getHotelId()).equals("fakeHotelId");
        assert(foundBookingsSize).equals(foundBookings.size());
    }

    @Test
    public void findBookingTest(){
        Booking foundBooking = bookingService.findBooking(testUser, testBookingOne.getHotelId());

        Mockito.verify(bookingRepository).findByUserAndHotelId(testUser, testBookingOne.getHotelId());
        assert(foundBooking.getHotelName()).equals(testBookingOne.getHotelName());
        assert(foundBooking.getHotelId()).equals(testBookingOne.getHotelId());
    }

    @Test
    public void deleteBookingTest(){
        bookingService.deleteBooking(testBookingOne);

        Mockito.verify(bookingRepository).delete(testBookingOne);

    }
}
