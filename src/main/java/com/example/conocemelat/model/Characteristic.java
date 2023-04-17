package com.example.conocemelat.model;

import javax.persistence.*;

@Entity
@Table(name="characteristics")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_characteristic")
    private Long idCharacteristic;

    @Column(name = "characteristic_name", nullable = false, unique = true)
    private String characteristicName;

    @Column(name = "characteristic_image", nullable = false, unique = true)
    private String characteristicImage;

    @Column(name = "characteristic_description", nullable = false, unique = true)
    private String characteristicDescription;

    public Characteristic() {
    }

    public Characteristic(Long idCharacteristic, String characteristicName, String characteristicImage, String characteristicDescription) {
        this.idCharacteristic = idCharacteristic;
        this.characteristicName = characteristicName;
        this.characteristicImage = characteristicImage;
        this.characteristicDescription = characteristicDescription;
    }

    public Characteristic(String characteristicName, String characteristicImage, String characteristicDescription) {
        this.characteristicName = characteristicName;
        this.characteristicImage = characteristicImage;
        this.characteristicDescription = characteristicDescription;
    }

    public Long getIdCharacteristic() {
        return idCharacteristic;
    }

    public void setIdCharacteristic(Long idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getCharacteristicImage() {
        return characteristicImage;
    }

    public void setCharacteristicImage(String characteristicImage) {
        this.characteristicImage = characteristicImage;
    }

    public String getCharacteristicDescription() {
        return characteristicDescription;
    }

    public void setCharacteristicDescription(String characteristicDescription) {
        this.characteristicDescription = characteristicDescription;
    }
}
