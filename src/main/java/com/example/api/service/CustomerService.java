package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final AddressService addressService;


    public List<Customer> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAllByOrderByNameAsc(pageable);
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public Customer create(Customer customer) {
        log.info("Creating customer {}", customer);
        customer.getAddresses().forEach(address -> {
            address.setCustomer(customer);
            addressService.loadByZipCode(address);
        });

        return repository.save(customer);
    }

    public Optional<Customer> update(Long id, Customer customer) {
        log.info("Updating customer {} with data {}", id, customer);

        return repository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.getAddresses().clear();
            customer.getAddresses().forEach(address -> {
                addressService.loadByZipCode(address);
                existingCustomer.addAddress(address);
            });
            return repository.save(existingCustomer);
        });
    }

    public Optional<Void> delete(Long id) {
        log.info("Deleting customer {}", id);
        return repository.findById(id).map(customer -> {
            repository.delete(customer);
            return null;
        });
    }
}
