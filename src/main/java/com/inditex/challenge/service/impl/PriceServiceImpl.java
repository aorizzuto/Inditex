package com.inditex.challenge.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.challenge.converter.PriceConverter;
import com.inditex.challenge.dto.PriceDTO;
import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.repository.IPricesRepository;
import com.inditex.challenge.repository.model.Price;
import com.inditex.challenge.service.IPriceService;
import com.inditex.challenge.validator.ChainValidator;
import com.inditex.challenge.validator.DateValidator;
import com.inditex.challenge.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PriceServiceImpl implements IPriceService {

    IPricesRepository pricesRepository;
    Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
    ObjectMapper mapper = new ObjectMapper();

    public PriceServiceImpl(IPricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public void validate(String applyDate, Integer productId, Integer chainId) throws Exception {
        logger.info("Validating request");
        DateValidator.validateFormat(applyDate);
        ProductValidator.validate(productId);
        ChainValidator.validate(chainId);
        logger.info("Validate success");
    }

    @Override
    public PriceResultDTO process(String applyDate, Integer productId, Integer chainId) throws JsonProcessingException {
        logger.info("Process begin with applyDate: " + applyDate + ", productId: " + productId.toString() + " and chainId: "+ chainId);

        List<PriceDTO> records = getRecords(productId, applyDate);

        if (records.size() == 0) return new PriceResultDTO();

        PriceDTO priorityRecord = getMaxPriorityRecord(records);
        PriceResultDTO response = PriceConverter.convertToResponse(priorityRecord);

        logger.info("Process finished with result: " + mapper.writeValueAsString(response));
        return response;
    }

    private PriceDTO getMaxPriorityRecord(List<PriceDTO> records) {
        logger.info("Searching max priority record");

        if (records.size() == 1) return records.get(0);

        return records.stream()
                .max(Comparator.comparingInt(PriceDTO::getPriority))
                .get();
    }

    private List<PriceDTO> getRecords(Integer productId, String applyDate) {
        logger.info("Getting records from repository");

        List<Price> records = pricesRepository.findRecordsByProductIdAndApplyDate(productId, applyDate);

        logger.info("Found " + records.size() + " records.");
        return PriceConverter.convertToDTO(records);
    }
}
