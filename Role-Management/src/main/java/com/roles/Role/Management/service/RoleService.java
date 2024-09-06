package com.roles.Role.Management.service;

import com.roles.Role.Management.dto.CreateRoleRequest;
import com.roles.Role.Management.dto.Response;
import com.roles.Role.Management.entity.Role;
import com.roles.Role.Management.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository repository;

    public Response createRole(CreateRoleRequest request){
        //create unique role.
        //check if the roleName exists
        Optional<Role> role = repository.findByRoleName(request.getRoleName());
        if (role.isPresent()){
            return Response.builder()
                    .responseMessage("RoleName must be unique")
                    .build();
        }
        Role newRole = Role.builder()
                .roleName(request.getRoleName())
                .description(request.getDescription())
                .build();

        repository.save(newRole);
        return Response.builder()
                .responseMessage("Success")
                .data(CreateRoleRequest.builder()
                        .roleName(newRole.getRoleName())
                        .description(newRole.getDescription())
                        .build())
                .build();

    }

    public Response getAllRoles(){
        List<Role> roles = repository.findAll();
        //List of Role to List of CreateRoleRequest
        List<CreateRoleRequest> dataList = roles.stream().map(role -> {
            CreateRoleRequest createRoleRequest = new CreateRoleRequest();
            createRoleRequest.setRoleName(role.getRoleName());
            createRoleRequest.setDescription(role.getDescription());

            return createRoleRequest;
        }).toList();

        return Response.builder()
                .responseMessage("Success")
                .dataList(dataList)
                .build();
    }

    public Response getRoleByRoleName(String roleName){
        Optional<Role> role = repository.findByRoleName(roleName);
        if (role.isEmpty()){
            return Response.builder()
                    .responseMessage("Role does not exist")
                    .build();
        }
        CreateRoleRequest createRoleRequest = CreateRoleRequest.builder()
                .roleName(role.get().getRoleName())
                .description(role.get().getDescription())
                .build();
        return Response.builder()
                .responseMessage("Success")
                .data(createRoleRequest)
                .build();
    }

    /**
     * delete, update, getById
     */
}
