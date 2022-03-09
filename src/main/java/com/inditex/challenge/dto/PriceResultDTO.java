package com.inditex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResultDTO {

    @JsonProperty("product_id")
    Integer productId;

    @JsonProperty("chain_id")
    Integer chainId;

    @JsonProperty("aliquot_to_apply")
    Integer aliquotToApply;

    @JsonProperty("date_to_apply")
    DatesToApply dateToApply;

    @JsonProperty("final_price")
    BigDecimal finalPrice;
}
