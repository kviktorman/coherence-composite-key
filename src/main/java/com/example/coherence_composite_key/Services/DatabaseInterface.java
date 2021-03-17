package com.example.coherence_composite_key.Services;

import com.example.coherence_composite_key.Models.ExecutionStatusMessage;
import com.example.coherence_composite_key.Models.OperationModel;
import com.example.coherence_composite_key.Models.UserModel;

public interface DatabaseInterface {
    
    public ExecutionStatusMessage populateData();
    public OperationModel getCompositeKeyObject();
    public UserModel getSimpleKeyObject();
    
}
