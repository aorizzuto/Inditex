package com.inditex.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.exception.business.BusinessException;

public interface IPriceService {

    void validate(String applyDate, Integer productId, Integer chainId) throws BusinessException;

    PriceResultDTO process(String applyDate, Integer productId, Integer chainId) throws JsonProcessingException;
}
