package com.example.conocemelat.repository;

import com.example.conocemelat.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM Booking p WHERE p.check_in = ?1 AND p.check_out = ?2", nativeQuery = true)
    Booking findBookingByDate(Date checkIn, Date checkOut);

    @Query("SELECT b FROM Booking b WHERE b.product.id = ?1")
    List<Booking> findAllBookingsByProduct(Long id_product);

    @Query("SELECT b FROM Booking b WHERE b.user.id = ?1")
    List<Booking> findAllBookingsByUser(Long id_user);

}
