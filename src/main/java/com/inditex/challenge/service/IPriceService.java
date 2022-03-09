package com.inditex.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inditex.challenge.dto.PriceResultDTO;

public interface IPriceService {

    void validate(String applyDate, Integer productId, Integer chainId) throws Exception;

    PriceResultDTO process(String applyDate, Integer productId, Integer chainId) throws JsonProcessingException;
}
