package com.hotelapp.hotel.repositoryTest;


import com.hotelapp.hotel.model.Booking;
import com.hotelapp.hotel.model.User;
import com.hotelapp.hotel.repository.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    @Transactional
    public void findByUserTest(){
        //given
        User testUser = new User ("testName", "testPassword");
        entityManager.persist(testUser);

        Booking testBookingOne = new Booking("testHotel","testHotelId", testUser);
        bookingRepository.save(testBookingOne);
        Booking testBookingTwo = new Booking("testMotel","testMotelId", testUser);
        bookingRepository.save(testBookingTwo);

        List<Booking> testBookings = bookingRepository.findByUser(testUser);

        //when
        List<Booking> foundBookings = bookingRepository.findByUser(testUser);

        //then
        assert (foundBookings.contains(testBookingOne));
        assert (foundBookings.contains(testBookingTwo));
        assertEquals(foundBookings.size(), testBookings.size());

        entityManager.flush();
    }

    @Test
    public void findByUserAndHotelIdTest(){
        //given
        User testUser = new User ("testName", "testPassword");
        Booking testBooking = new Booking("testHotel","testHotelId", testUser);
        entityManager.persist(testUser);
        entityManager.persist(testBooking);

        //when
        Booking foundBooking = bookingRepository.findByUserAndHotelId(testUser, testBooking.getHotelId());

        //then
        assert (foundBooking.getReservationId()).equals(testBooking.getReservationId());
        assert (foundBooking.getHotelId()).equals(testBooking.getHotelId());
        assert (foundBooking.getHotelName()).equals(testBooking.getHotelName());
        assert (foundBooking.getUser()).equals(testBooking.getUser());

        entityManager.flush();
    }
}
