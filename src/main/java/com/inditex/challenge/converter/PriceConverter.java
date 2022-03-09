package com.inditex.challenge.converter;

import com.inditex.challenge.dto.DatesToApply;
import com.inditex.challenge.dto.PriceDTO;
import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.repository.model.Price;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PriceConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static List<Price> convertToEntity(List<PriceDTO> listOfPrices) {
        return listOfPrices.stream()
                .map(PriceConverter::toEntity)
                .collect(Collectors.toList());
    }

    public static List<PriceDTO> convertToDTO(List<Price> listOfPrices) {
        return listOfPrices.stream()
                .map(PriceConverter::toDto)
                .collect(Collectors.toList());
    }

    private static Price toEntity(PriceDTO priceDTO) {
        return modelMapper.map(priceDTO, Price.class);
    }

    private static PriceDTO toDto(Price price) {
        return modelMapper.map(price, PriceDTO.class);
    }

    public static PriceResultDTO convertToResponse(PriceDTO price) {
        return PriceResultDTO.builder()
                .productId(price.getProductId())
                .chainId(price.getBrandId())
                .dateToApply(getDatesToApply(price))
                .aliquotToApply(price.getPriceList())
                .finalPrice(price.getPrice())
                .build();
    }

    private static DatesToApply getDatesToApply(PriceDTO price) {
        return new DatesToApply(price.getStartDate(), price.getEndDate());
    }
}
