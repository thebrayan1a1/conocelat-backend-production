package com.example.conocemelat.service.impl;

import com.example.conocemelat.model.City;
import com.example.conocemelat.repository.CityRepository;
import com.example.conocemelat.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    public CityRepository cityRepository;

    @Override
    public List<City> listAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
