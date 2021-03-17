package com.example.coherence_composite_key.InterfacesDAO;

import com.example.coherence_composite_key.ModelsDAO.UserModelDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModelDAO, Integer> {
    
}
