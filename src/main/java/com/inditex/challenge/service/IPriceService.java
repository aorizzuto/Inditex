package com.inditex.challenge.service;

import com.inditex.challenge.dto.PriceResultDTO;

import java.util.List;

public interface IPriceService {

    void validate(String applyDate, Integer productId, Integer chainId);

    List<PriceResultDTO> process(String applyDate, Integer productId, Integer chainId);
}
