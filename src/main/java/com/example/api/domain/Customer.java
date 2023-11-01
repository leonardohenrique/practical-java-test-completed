package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    @Valid
    @JsonIgnoreProperties({"customer"})
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        address.setCustomer(this);
        addresses.add(address);
    }
}
