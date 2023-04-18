package com.example.conocemelat.controller;

import com.example.conocemelat.model.City;
import com.example.conocemelat.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<City> saveCity(@RequestBody City city){
        return ResponseEntity.ok(cityService.saveCity(city));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<City>> listAllCities(){
        return ResponseEntity.ok(cityService.listAllCities());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<City> getCityById(@PathVariable Integer id){
        ResponseEntity<City> response;

        if (cityService.getCityById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(cityService.getCityById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<City> updateCity(@RequestBody City city){
        ResponseEntity<City> response;
        if (city.getIdCity() != null && cityService.getCityById(city.getIdCity()) != null){
            response = ResponseEntity.ok(cityService.saveCity(city));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<City> deleteCity(@PathVariable Integer id){
        ResponseEntity<City> response;
        if (cityService.getCityById(Long.valueOf(id)) != null){
            cityService.deleteCity(Long.valueOf(id));
            response = ResponseEntity.ok().build();
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    
}
