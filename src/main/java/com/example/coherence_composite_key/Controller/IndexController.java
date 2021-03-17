package com.example.coherence_composite_key.Controller;

import com.example.coherence_composite_key.Models.OperationModel;
import com.example.coherence_composite_key.Models.ExecutionStatusMessage;
import com.example.coherence_composite_key.Models.UserModel;
import com.example.coherence_composite_key.Services.DatabaseInterface;
import com.example.coherence_composite_key.Services.H2DatabaseService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional()
public class IndexController {
    
    private DatabaseInterface databaseService;

    @Autowired
    public void setDatabaseService(H2DatabaseService databaseService) {
        this.databaseService = databaseService;
    }    
    
    @GetMapping(path = "/populate-database", produces = "application/json")
    public ExecutionStatusMessage getPopulateDatabase() {
         return this.databaseService.populateData();
    }
    
    @GetMapping(path = "/get-composite-key-object", produces = "application/json")
    public OperationModel getCompositeKeyObject() {
         return this.databaseService.getCompositeKeyObject();
    }

    @GetMapping(path = "/get-simple-key-object", produces = "application/json")
    public UserModel getSimpleKeyObject() {
         return this.databaseService.getSimpleKeyObject();
    }    
    
}
