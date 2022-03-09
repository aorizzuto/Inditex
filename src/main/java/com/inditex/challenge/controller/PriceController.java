package com.inditex.challenge.controller;

import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.service.impl.PriceServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    PriceServiceImpl priceService;

    public PriceController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/price")
    List<PriceResultDTO> getProducts(
            @RequestParam String applyDate,
            @RequestParam Integer productId,
            @RequestParam Integer chainId
    ) {
        // In case the number of parameters is higher, then we need to use a Wrapper. Because passing too many arguments to a method it will be end as a Code Smell
        priceService.validate(applyDate, productId, chainId);
        return priceService.process(applyDate, productId, chainId);
    }
}
