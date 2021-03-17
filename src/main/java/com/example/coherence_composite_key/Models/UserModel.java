package com.example.coherence_composite_key.Models;

import com.example.coherence_composite_key.ModelsDAO.UserModelDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {
    @JsonProperty("userId")
    public Integer userId;
    @JsonProperty("otherField")
    public String otherField;

    public UserModel(Integer userId, String otherField) {
        this.userId = userId;
        this.otherField = otherField;
    }
    
    public UserModel(UserModelDAO databaseObject){
        this.userId = databaseObject.getUserId();
        this.otherField = databaseObject.getOtherField();
    }
    
}
