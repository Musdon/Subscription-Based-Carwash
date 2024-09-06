package com.roles.Role.Management.controller;

import com.roles.Role.Management.dto.CreateRoleRequest;
import com.roles.Role.Management.dto.Response;
import com.roles.Role.Management.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("roles")
public class RoleController {

    private RoleService service;

    @PostMapping
    public ResponseEntity<Response> createRole(@RequestBody CreateRoleRequest request){
        Response response = service.createRole(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response> getAllRoles(){
        return ResponseEntity.ok(service.getAllRoles());
    }

}
