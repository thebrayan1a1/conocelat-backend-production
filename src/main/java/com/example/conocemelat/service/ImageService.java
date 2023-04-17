package com.example.conocemelat.service;

import com.example.conocemelat.model.Image;

import java.util.List;

public interface ImageService {
    public List<Image> listAllImages();

    public Image saveImage(Image image);

    public Image getImageById(Long id);

    public Image updateImage(Image image);

    public void deleteImage(Long id);

    public List<Image> findAllImagesByProduct(Long id);
}
