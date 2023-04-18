package com.example.conocemelat.controller;

import com.example.conocemelat.model.Category;
import com.example.conocemelat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Category>> listAllCategories(){
        return ResponseEntity.ok(categoryService.listAllCategories());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){
        ResponseEntity<Category> response;

        if (categoryService.getCategoryById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(categoryService.getCategoryById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        ResponseEntity<Category> response;
        if (category.getIdCategory() != null && categoryService.getCategoryById(category.getIdCategory()) != null){
            response = ResponseEntity.ok(categoryService.saveCategory(category));
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
        ResponseEntity<Category> response;
        if (categoryService.getCategoryById(Long.valueOf(id)) != null){
            categoryService.deleteCategory(Long.valueOf(id));
            response = ResponseEntity.ok().build();
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
    
}
