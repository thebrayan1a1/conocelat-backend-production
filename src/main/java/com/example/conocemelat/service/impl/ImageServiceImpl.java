package com.example.conocemelat.service.impl;

import com.example.conocemelat.model.Image;
import com.example.conocemelat.repository.ImageRepository;
import com.example.conocemelat.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    public ImageRepository imageRepository;

    @Override
    public List<Image> listAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public Image updateImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> findAllImagesByProduct(Long id) {
        return imageRepository.findAllImagesByProduct(id);
    }
}
