package com.inditex.challenge.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.challenge.dto.PriceDTO;
import com.inditex.challenge.repository.model.Price;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    String path = "";
    ObjectMapper objectMapper = new ObjectMapper();
           // .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    JsonNode json = null;

    public JsonUtils(String path){
        this.path = path;
    }

    private JsonUtils loadFile() throws IOException {
        InputStream file = getClass().getClassLoader().getResourceAsStream(path);
        json = objectMapper.readTree(file);
        return this;
    }

    private String responseBody(String option) {
        return json.get(option).toString();
    }

    private List<PriceDTO> toListOfPrices() throws JsonProcessingException {
        //return objectMapper.readValue(responseBody("prices"), new TypeReference<>() {});
        return Arrays.asList(objectMapper.readValue(responseBody("prices"), PriceDTO[].class));
    }

    public List<PriceDTO> getListOfPrices() throws IOException {
        return loadFile().toListOfPrices();
    }

    public <T> String getObjectAsString(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
