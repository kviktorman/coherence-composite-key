package com.example.coherence_composite_key.InterfacesDAO;

import com.example.coherence_composite_key.ModelsDAO.OperationModelDAO;
import com.example.coherence_composite_key.ModelsDAO.OperationModelPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationModelDAO, OperationModelPK> {
    
}
