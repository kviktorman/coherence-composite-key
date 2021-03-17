package com.example.coherence_composite_key.Services;

import com.example.coherence_composite_key.Constants.TestConstants;
import com.example.coherence_composite_key.InterfacesDAO.OperationRepository;
import com.example.coherence_composite_key.InterfacesDAO.UserRepository;
import com.example.coherence_composite_key.Models.ExecutionStatusMessage;
import com.example.coherence_composite_key.Models.OperationModel;
import com.example.coherence_composite_key.Models.UserModel;
import com.example.coherence_composite_key.ModelsDAO.OperationModelDAO;
import com.example.coherence_composite_key.ModelsDAO.OperationModelPK;
import com.example.coherence_composite_key.ModelsDAO.UserModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class H2DatabaseService implements DatabaseInterface {

    private UserRepository userRepository;
    private OperationRepository operationRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }
    
    @Override
    public ExecutionStatusMessage populateData() {

        // add one user to DB
        UserModelDAO user = new UserModelDAO()
                .setUserId(TestConstants.TEST_USER_ID)
                .setOtherField(TestConstants.TEST_USER_OTHER_FIELD);
        
        this.userRepository.save(user);

        // add one operation to DB
        OperationModelDAO operation = new OperationModelDAO()
                .setUserId(TestConstants.TEST_USER_ID)
                .setIdOperation(TestConstants.TEST_OPERATION_ID)
                .setComment(TestConstants.TEST_OPERATION_COMMENT);
        
        this.operationRepository.save(operation);

        return new ExecutionStatusMessage(Boolean.TRUE);
    }

    @Override
    public OperationModel getCompositeKeyObject() {
        
        OperationModelPK operationIdentifier=new OperationModelPK()
                .setUserId(TestConstants.TEST_USER_ID)
                .setIdOperation(TestConstants.TEST_OPERATION_ID);
                
        // retrieve database object
        OperationModelDAO retrievedObject = this.operationRepository.getOne(operationIdentifier);
       
        // convert and return final object
        return new OperationModel(retrievedObject);
    }

    @Override
    public UserModel getSimpleKeyObject() {

        // retrieve database object
        UserModelDAO retrievedObject = this.userRepository.getOne(TestConstants.TEST_USER_ID);
        
        // convert and return final object
        return new UserModel(retrievedObject);
    }
}
