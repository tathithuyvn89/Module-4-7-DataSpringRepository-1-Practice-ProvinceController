package com.codegym.service;


import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
