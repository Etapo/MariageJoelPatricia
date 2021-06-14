/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 190)
    @Column(name = "id", nullable = false, length = 190)
    private String id;

    @Column(name = "date_creation")
    private Long dateCreation;

    @Column(name = "last_date_update")
    private Long lastDateUpdate;

    @Column(name = "user_creation", length = 190)
    private String userCreation;

    @Column(name = "last_user_update", length = 190)
    private String lastUserUpdate;

    @Column(name = "entity_state")
    private Boolean entityState;

    @Column(name = "flag", nullable = false)
    private Boolean flag;

    public AbstractEntity() {
    }
    
    public AbstractEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getLastDateUpdate() {
        return lastDateUpdate;
    }

    public void setLastDateUpdate(Long lastDateUpdate) {
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
