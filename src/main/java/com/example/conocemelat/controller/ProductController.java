package com.example.conocemelat.controller;

import com.example.conocemelat.model.Product;
import com.example.conocemelat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Product>> listAllProducts(){
        return ResponseEntity.ok(productService.listAllProducts());
    }

    @GetMapping("/random-list")
    @PermitAll
    public ResponseEntity<List<Product>> listRandomProducts(){
        return ResponseEntity.ok(productService.listRandomProducts());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        ResponseEntity<Product> response;

        if (productService.getProductById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(productService.getProductById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        ResponseEntity<Product> response;
        if (product.getIdProduct() != null && productService.getProductById(product.getIdProduct()) != null){
            response = ResponseEntity.ok(productService.saveProduct(product));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted");
    }

    @GetMapping("/category/{id}")
    @PermitAll
    public ResponseEntity<List<Product>> listAllProductsByCategory(@PathVariable Long id){
        return ResponseEntity.ok(productService.findAllProductsByCategory(id));
    }

    @GetMapping("/city/{id}")
    @PermitAll
    public ResponseEntity<List<Product>> listAllProductsByCity(@PathVariable Long id){
        return ResponseEntity.ok(productService.findAllProductsByCity(id));
    }

    @GetMapping("/search-date")
    public ResponseEntity<List<Product>> findProductsByDate(@RequestParam(value = "date_in", required = false) String checkIn,
                                                            @RequestParam(value = "date_out", required = false)String checkOut) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date dateIn = simpleDateFormat.parse(checkIn);
        Date dateOut = simpleDateFormat.parse(checkOut);

        return ResponseEntity.ok(productService.findProductsByDate(dateIn, dateOut));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByParams(@RequestParam (value = "date_in", required = false) String checkInDay,
                                                        @RequestParam (value = "date_out", required = false) String checkOutDay,
                                                        @RequestParam (value = "id_city", required = false) Long idCity) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date dateIn = simpleDateFormat.parse(checkInDay);
        Date dateOut = simpleDateFormat.parse(checkOutDay);

        return ResponseEntity.ok(productService.findAllProductsByCityAndDate(dateIn, dateOut, idCity));

    }


}
