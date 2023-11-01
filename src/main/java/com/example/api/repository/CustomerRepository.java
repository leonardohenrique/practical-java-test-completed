package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAllByOrderByNameAsc();

    Page<Customer> findAllByOrderByNameAsc(Pageable pageable);

}
