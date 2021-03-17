package com.example.coherence_composite_key.ModelsDAO;

import java.io.Serializable;
import java.util.Objects;

public class OperationModelPK implements Serializable {
    
    private Integer userId;
    private Integer idOperation;

    public OperationModelPK() {
    }

    public OperationModelPK(Integer userId, Integer idOperation) {
        this.userId = userId;
        this.idOperation = idOperation;
    }

    public Integer getUserId() {
        return userId;
    }

    public OperationModelPK setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public OperationModelPK setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.userId);
        hash = 13 * hash + Objects.hashCode(this.idOperation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OperationModelPK other = (OperationModelPK) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.idOperation, other.idOperation)) {
            return false;
        }
        return true;
    }

  
    
    
}
