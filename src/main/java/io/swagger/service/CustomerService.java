package io.swagger.service;

import io.swagger.entity.CustomerEntity;
import io.swagger.model.NewCustomer;
import io.swagger.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void addCustomer(NewCustomer dto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        repository.save(customer);
    }
    public List<CustomerEntity> getAllCustomers() {
        return repository.findAll();
    }


}
