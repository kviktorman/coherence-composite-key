package com.example.coherence_composite_key.Models;

import com.example.coherence_composite_key.ModelsDAO.OperationModelDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationModel {

    @JsonProperty("userId")
    public Integer userId;
    @JsonProperty("idOperation")
    public Integer idOperation;
    @JsonProperty("comment")
    public String comment;

    public OperationModel(Integer userId, Integer idOperation, String comment) {
        this.userId = userId;
        this.idOperation = idOperation;
        this.comment = comment;
    }

    public OperationModel(OperationModelDAO databaseObject) {
        this.userId = databaseObject.getUserId();
        this.idOperation = databaseObject.getIdOperation();
        this.comment = databaseObject.getComment();
    }

}
