package com.example.coherence_composite_key.ModelsDAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@IdClass(OperationModelPK.class)
@Table(name = "OPERATIONS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperationModelDAO {
    @Id
    @Column(name = "USER_ID")
    private Integer userId;
    @Id
    @Column(name = "ID_OPERATION")
    private Integer idOperation;
    @Column(name = "COMMENT")
    private String comment;

    public Integer getUserId() {
        return userId;
    }

    public OperationModelDAO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public OperationModelDAO setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public OperationModelDAO setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public OperationModelDAO() {
    }
    
}
