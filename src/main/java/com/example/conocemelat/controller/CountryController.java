package com.example.conocemelat.controller;

import com.example.conocemelat.model.Country;
import com.example.conocemelat.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<Country> saveCountry(@RequestBody Country country){
        return ResponseEntity.ok(countryService.saveCountry(country));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Country>> listAllCountries(){
        return ResponseEntity.ok(countryService.listAllCountries());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id){
        ResponseEntity<Country> response;

        if (countryService.getCountryById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(countryService.getCountryById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Country> updateCountry(@RequestBody Country country){
        ResponseEntity<Country> response;
        if (country.getIdCountry() != null && countryService.getCountryById(country.getIdCountry()) != null){
            response = ResponseEntity.ok(countryService.saveCountry(country));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<Country> deleteCountry(@PathVariable Integer id){
        ResponseEntity<Country> response;
        if (countryService.getCountryById(Long.valueOf(id)) != null){
            countryService.deleteCountry(Long.valueOf(id));
            response = ResponseEntity.ok().build();
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    
}
