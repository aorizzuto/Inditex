package com.inditex.challenge.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class ResponseEntityBody {

    private String message;
    private String errorCode;

    public ResponseEntityBody(String message, @JsonProperty("error_code") String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ResponseEntityBody(String message){
        this.message = message;
        this.errorCode = "400";
    }
}
