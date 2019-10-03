package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PersistenceCustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findOne(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> save(List<Customer> customers) {
        return customers.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return findOne(id) != null;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return ids.stream().map(this::findOne).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public void delete(Long id) {
        customerRepository.remove(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.remove(customer.getId());
    }

    @Override
    public void delete(List<Customer> customers) {
        customers.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        delete(findAll());
    }
}
