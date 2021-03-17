package com.example.coherence_composite_key.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionStatusMessage {

    @JsonProperty("executionIsSuccessful")
    public Boolean executionIsSuccessful;

    public ExecutionStatusMessage(Boolean executionIsSuccessful) {
        this.executionIsSuccessful = executionIsSuccessful;
    }
    
    
}
