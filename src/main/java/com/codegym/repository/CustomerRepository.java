package com.codegym.repository;



import java.util.List;
import com.codegym.model.Customer;

public interface CustomerRepository extends Repository<Customer> {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer model);

    void remove(Long id);
}
