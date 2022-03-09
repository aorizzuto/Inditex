package com.inditex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceDTO {

    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("price_list")
    private Integer priceList;

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("priority")
    private Integer priority;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("currency")
    private String currency;

}
