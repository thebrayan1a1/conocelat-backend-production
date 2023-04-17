package com.example.conocemelat.model;

import javax.persistence.*;

@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    private Long idCountry;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    public Country() {
    }

    public Country(Long idCountry, String countryName) {
        this.idCountry = idCountry;
        this.countryName = countryName;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
