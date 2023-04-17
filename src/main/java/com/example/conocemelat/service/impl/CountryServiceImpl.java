package com.example.conocemelat.service.impl;

import org.springframework.stereotype.Service;

import com.example.conocemelat.model.Country;
import com.example.conocemelat.repository.CountryRepository;
import com.example.conocemelat.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    public CountryRepository countryRepository;

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).get();
    }

    @Override
    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
