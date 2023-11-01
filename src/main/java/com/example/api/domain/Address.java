package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String country;

    private String neighborhood;

    private String zipCode;

    @JsonIgnoreProperties({"addresses"})
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
