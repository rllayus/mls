package com.laredo;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements Serializable,Cloneable {

    @CreatedDate
    protected Date createdDate;

    @LastModifiedDate
    protected Date modifiedDate;

    @CreatedBy
    protected String createdBy;

    @LastModifiedBy
    protected String modifiedBy;

    @Version
    protected  Long version;


    public Object clone()throws CloneNotSupportedException{
        return (AuditableEntity)super.clone();
    }


}
