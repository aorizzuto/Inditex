package com.inditex.challenge.converter;

import com.inditex.challenge.dto.PriceDTO;
import com.inditex.challenge.repository.model.Price;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PriceConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static List<Price> convert(List<PriceDTO> listOfPrices) {
        return listOfPrices.stream()
                .map(PriceConverter::convertToEntity)
                .collect(Collectors.toList());
    }

    private static Price convertToEntity(PriceDTO priceDTO) {
        return modelMapper.map(priceDTO, Price.class);
    }

    private static PriceDTO convertToDto(Price price) {
        return modelMapper.map(price, PriceDTO.class);
    }
}
