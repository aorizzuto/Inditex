package com.inditex.challenge.service.impl;

import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.service.IPriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements IPriceService {

    @Override
    public void validate(String applyDate, Integer productId, Integer chainId) {

    }

    @Override
    public List<PriceResultDTO> process(String applyDate, Integer productId, Integer chainId) {
        return null;
    }
}
