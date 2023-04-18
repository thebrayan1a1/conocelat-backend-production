package com.example.conocemelat.model;

import javax.persistence.*;

@Entity
@Table(name="cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Long idCity;

    @Column(name = "city_name", nullable = false, unique = true)
    private String cityName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "id_country")
    private Country country;

    public City() {
    }

    public City(Long idCity, String cityName, Country country) {
        this.idCity = idCity;
        this.cityName = cityName;
        this.country = country;
    }

    public City(String cityName, Country country) {
        this.cityName = cityName;
        this.country = country;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
