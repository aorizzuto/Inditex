package com.inditex.challenge.controller;

import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.repository.IPricesRepository;
import com.inditex.challenge.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {PriceController.class})
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IPricesRepository pricesRepository;

    @MockBean
    PriceServiceImpl priceService;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void GIVEN_a_valid_request_WHEN_endpoint_called_THEN_success() throws Exception {
        String applyDate = "2020-03-04-00.00.00";
        Integer productId = 1;
        Integer chainId = 1;

        Mockito.when(priceService.process(applyDate, productId, chainId))
                .thenReturn(PriceResultDTO.builder().build());

        mockMvc
                .perform(
                        get("/price")
                                .param("apply_date", applyDate)
                                .param("product_id", productId.toString())
                                .param("chain_id", chainId.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is(200));
    }

    @Test
    public void GIVEN_a_invalid_request_WHEN_endpoint_called_THEN_fail() throws Exception {
        String applyDate = "20-03-04-00.00.00";
        Integer productId = -1;
        Integer chainId = 1;

        Mockito.when(priceService.process(applyDate, productId, chainId)).thenCallRealMethod();

        mockMvc
                .perform(
                        get("/price")
                                .param("apply_date", applyDate)
                                .param("product_id", productId.toString())
                                .param("chain_id", chainId.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is(404));
    }
}
