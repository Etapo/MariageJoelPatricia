/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Denis ETABA<sylvainonguene@gmail.com>
 */
@Entity
@Table(name = "mariage_joel_patricia_table_guest")
public class TableGuest extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 10, max = 190)
    @Column(name = "name", length = 190, nullable = false)
    private String name;

    @Column(name = "number_place", nullable = false)
    private Integer numberPlace;
    
    public TableGuest() {
    }

    public TableGuest(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(Integer numberPlace) {
        this.numberPlace = numberPlace;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableGuest other = (TableGuest) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "TableGuest{" + "id=" + this.getId() + ", dateCreation=" + this.getDateCreation() + ", lastDateUpdate=" + this.getLastDateUpdate() + ", userCreation=" + this.getUserCreation() + ", lastUserUpdate=" + this.getLastUserUpdate() + ", entityState=" + this.getEntityState() + ", flag=" + this.getFlag() + "name=" + name + ", numberPlace=" + numberPlace + '}';
    }
    
    

}
