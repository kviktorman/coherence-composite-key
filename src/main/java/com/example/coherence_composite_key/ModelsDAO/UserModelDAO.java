package com.example.coherence_composite_key.ModelsDAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USERS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserModelDAO {

    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "OTHER_FIELD")
    private String otherField;

    public Integer getUserId() {
        return userId;
    }

    public UserModelDAO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getOtherField() {
        return otherField;
    }

    public UserModelDAO setOtherField(String otherField) {
        this.otherField = otherField;
        return this;
    }

    public UserModelDAO() {
    }
    
    
}
