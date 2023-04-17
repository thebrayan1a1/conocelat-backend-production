package com.example.conocemelat.controller;

import com.example.conocemelat.model.Image;
import com.example.conocemelat.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<Image> saveImage(@RequestBody Image image){
        return ResponseEntity.ok(imageService.saveImage(image));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Image>> listAllImage(){
        return ResponseEntity.ok(imageService.listAllImages());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Image> getImageById(@PathVariable Integer id){
        ResponseEntity<Image> response;

        if (imageService.getImageById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(imageService.getImageById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
    @GetMapping("/product/{id}")
    @PermitAll
    public ResponseEntity<List<Image>> findAllImagesByProduct(@PathVariable Long id){
        return ResponseEntity.ok(imageService.findAllImagesByProduct(id));
    }
}
