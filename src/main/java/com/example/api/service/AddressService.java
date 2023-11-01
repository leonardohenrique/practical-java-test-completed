package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.service.dto.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ViaCepService viaCepService;

    public void loadByZipCode(Address address) {
        if (address.getZipCode() == null) {
            return;
        }

        ViaCepDTO viaCepDTO = viaCepService.get(address.getZipCode());
        if (viaCepDTO != null && viaCepDTO.getCep() != null) {
            address.setStreet(viaCepDTO.getLogradouro());
            address.setState(viaCepDTO.getUf());
            address.setCity(viaCepDTO.getLocalidade());
            address.setNeighborhood(viaCepDTO.getBairro());
            address.setCountry("Brasil");
        }
    }


}
