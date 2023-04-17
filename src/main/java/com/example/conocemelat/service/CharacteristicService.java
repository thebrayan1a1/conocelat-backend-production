package com.example.conocemelat.service;

import com.example.conocemelat.model.Characteristic;

import java.util.List;


public interface CharacteristicService {



    public List<Characteristic> listAllCharacteristic();

    public Characteristic saveCharacteristic(Characteristic characteristic);

    public Characteristic getCharacteristicById(Long id);

    public Characteristic updateCharacteristic(Characteristic characteristic);

    public void deleteCharacteristic(Long id);
}
