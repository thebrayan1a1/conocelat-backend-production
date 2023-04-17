package com.example.conocemelat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="booking")
public class Booking implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long idBooking;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "check_in", nullable = false, unique = false)
    private Date checkIn;

    @Column(name = "check_out", nullable = false, unique = false)
    private Date checkOut;

    @Column(name = "total", nullable = false, unique = false)
    private Double total;

    public Booking() {
    }

    public Booking(Long idBooking, User user, Product product, Date checkIn, Date checkOut, Double total) {
        this.idBooking = idBooking;
        this.user = user;
        this.product = product;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
    }

    public Booking(User user, Product product, Date checkIn, Date checkOut, Double total) {
        this.user = user;
        this.product = product;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
    }

    public Long getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Long idBooking) {
        this.idBooking = idBooking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}