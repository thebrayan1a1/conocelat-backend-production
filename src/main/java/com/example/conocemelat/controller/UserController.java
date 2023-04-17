package com.example.conocemelat.controller;

import com.example.conocemelat.jwt.model.AuthenticationResquest;
import com.example.conocemelat.model.Role;
import com.example.conocemelat.model.User;
import com.example.conocemelat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<User> saveUser(@RequestBody User user){
        String passWEncrypt= passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(passWEncrypt);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<User>> listAllPUsers(){
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        ResponseEntity<User> response;

        if (userService.getUserById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(userService.getUserById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<User> updateUser(@RequestBody User user){
        ResponseEntity<User> response;
        if (user.getIdUser() != null && userService.getUserById(user.getIdUser()) != null){
            response = ResponseEntity.ok(userService.saveUser(user));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        userService.deleteUser(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted");
    }

    //@RequestMapping(value = "/login", method = RequestMethod.POST)
    //public String hello() {
        //return "Felicitaciones pudiste ingresar dentro de nuestra app";
    //}




}
