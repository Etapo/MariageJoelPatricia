/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.dto;

import com.mariage.joelpatricia.entities.AbstractEntity;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class EntityDto implements Serializable{

    private String id;
    private String dateCreation;
    private String lastDateUpdate;
    private String userCreation;
    private String lastUserUpdate;
    private Boolean entityState;
    private Boolean flag;

    public EntityDto() {
    }

    public EntityDto(String id) {
        this.id = id;
    }

    public EntityDto(AbstractEntity entity) {
        this(entity, "dd/MM/yyyy hh:mm:ss");
    }

    public EntityDto(AbstractEntity entity, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        this.id = entity.getId();
        this.dateCreation = dateFormat.format(entity.getDateCreation());
        this.lastDateUpdate = dateFormat.format(entity.getLastDateUpdate());
        this.userCreation = entity.getUserCreation();
        this.lastUserUpdate = entity.getLastUserUpdate();
        this.entityState = entity.getEntityState();
        this.flag = entity.getFlag();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getLastDateUpdate() {
        return lastDateUpdate;
    }

    public void setLastDateUpdate(String lastDateUpdate) {
        this.lastDateUpdate = lastDateUpdate;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getLastUserUpdate() {
        return lastUserUpdate;
    }

    public void setLastUserUpdate(String lastUserUpdate) {
        this.lastUserUpdate = lastUserUpdate;
    }

    public Boolean getEntityState() {
        return entityState;
    }

    public void setEntityState(Boolean entityState) {
        this.entityState = entityState;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

}
