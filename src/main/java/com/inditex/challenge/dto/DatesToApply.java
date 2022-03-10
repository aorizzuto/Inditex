package com.inditex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DatesToApply {
    @JsonProperty("start_date") String startDate;
    @JsonProperty("end_date") String endDate;
}
