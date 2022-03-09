package com.inditex.challenge.config;

import com.inditex.challenge.converter.PriceConverter;
import com.inditex.challenge.dto.PriceDTO;
import com.inditex.challenge.repository.IPricesRepository;
import com.inditex.challenge.repository.model.Price;
import com.inditex.challenge.utils.EnvironmentUtils;
import com.inditex.challenge.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
class InitConfig {

    IPricesRepository pricesRepository;
    EnvironmentUtils environmentUtils;

    public InitConfig(IPricesRepository pricesRepository, EnvironmentUtils environmentUtils) {
        this.pricesRepository = pricesRepository;
        this.environmentUtils = environmentUtils;
    }

    private Logger logger = LoggerFactory.getLogger(InitConfig.class);

    @EventListener(ApplicationReadyEvent.class)
    void afterStartUp() throws IOException {
        if (environmentUtils.isLocal()) {
            logger.info("Saving prices from initial json file");
            List<PriceDTO> listOfPrices = new JsonUtils("initial-inserts.json").getListOfPrices();
            List<Price> prices = PriceConverter.convert(listOfPrices);
//            pricesRepository.saveAll(prices); // This is the second way to create initial inserts. 1st way in data.sql
        }
    }
}
