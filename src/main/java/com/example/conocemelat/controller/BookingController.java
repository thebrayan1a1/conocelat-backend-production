package com.example.conocemelat.controller;

import com.example.conocemelat.model.Booking;
import com.example.conocemelat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking){
        return ResponseEntity.ok(bookingService.saveBooking(booking));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Booking>> listAllBookings(){
        return ResponseEntity.ok(bookingService.listAllBookings());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Booking> getBookingById(@PathVariable Integer id){
        ResponseEntity<Booking> response;

        if (bookingService.getBookingById(Long.valueOf(id))!=null) {
            response = ResponseEntity.ok(bookingService.getBookingById(Long.valueOf(id))) ;
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking){
        ResponseEntity<Booking> response;
        if (booking.getIdBooking() != null && bookingService.getBookingById(booking.getIdBooking()) != null) {
            response = ResponseEntity.ok(bookingService.saveBooking(booking));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted");
    }

    @GetMapping("/find")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<List<Booking>> findBookingsByDate(@RequestParam String checkIn, @RequestParam String checkOut) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = formatter.parse(checkIn);
        Date checkOutDate = formatter.parse(checkOut);
        return ResponseEntity.ok(bookingService.findBookingsByDate(checkInDate, checkOutDate));
    }

    @GetMapping("/product/{id}")
   @PermitAll
    public ResponseEntity<List<Booking>> listAllBookingsByProduct(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.findAllBookingsByProduct(id));
    }

    @GetMapping("/user/{id}")
    @PermitAll
    public ResponseEntity<List<Booking>> listAllBookingsByUser(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.findAllBookingsByUser(id));
    }

}
