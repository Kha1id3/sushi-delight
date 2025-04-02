package io.swagger.controller;

import io.swagger.api.CustomersApi;
import io.swagger.entity.CustomerEntity;
import io.swagger.model.NewCustomer;
import io.swagger.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomersApiController implements CustomersApi {

    @Autowired
    private CustomerService service;

    @Override
    public ResponseEntity<Void> customersPost(NewCustomer newCustomer) {
        service.addCustomer(newCustomer);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<List<NewCustomer>> customersGet() {
        List<NewCustomer> dtoList = service.getAllCustomers().stream().map(entity -> {
            NewCustomer dto = new NewCustomer();
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            dto.setPhone(entity.getPhone());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtoList);
    }


}
