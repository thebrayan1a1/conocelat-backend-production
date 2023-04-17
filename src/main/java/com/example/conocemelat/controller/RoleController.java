package com.example.conocemelat.controller;

import com.example.conocemelat.model.Role;
import com.example.conocemelat.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    //no tenia el permitAll
    @PermitAll
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(roleService.saveRole(role));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Role>> listAllRoles(){
        return ResponseEntity.ok(roleService.listAllRoles());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id){
        ResponseEntity<Role> response;

        if (roleService.getRoleById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(roleService.getRoleById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        ResponseEntity<Role> response;
        if (role.getIdRole() != null && roleService.getRoleById(role.getIdRole()) != null){
            response = ResponseEntity.ok(roleService.saveRole(role));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteRole(@PathVariable Integer id){
        roleService.deleteRole(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted");
    }

}
