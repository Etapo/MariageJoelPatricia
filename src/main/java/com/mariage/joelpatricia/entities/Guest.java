/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Denis ETABA<sylvainonguene@gmail.com>
 */
@Entity
@Table(name = "mariage_joel_patricia_guest")
public class Guest extends AbstractEntity {

    @NotNull
    @Size(min = 10, max = 190)
    @Column(name = "name", length = 190, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type_billet", length = 50, nullable = false)
    private String typeBillet;

    @JoinColumn(name = "table_guest", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private TableGuest tableGuest;

    @JoinColumn(name = "code_barre", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CodeBarre codeBarre;

    @Column(name = "present")
    private Boolean present;

    @Column(name = "status")
    private Boolean status;

    public Guest() {
    }

    public Guest(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeBillet() {
        return typeBillet;
    }

    public void setTypeBillet(String typeBillet) {
        this.typeBillet = typeBillet;
    }

    public TableGuest getTableGuest() {
        return tableGuest;
    }

    public void setTableGuest(TableGuest tableGuest) {
        this.tableGuest = tableGuest;
    }

    public CodeBarre getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(CodeBarre codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        final Guest other = (Guest) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "Guest{" + "id=" + this.getId() + ", dateCreation=" + this.getDateCreation() + ", lastDateUpdate=" + this.getLastDateUpdate() + ", userCreation=" + this.getUserCreation() + ", lastUserUpdate=" + this.getLastUserUpdate() + ", entityState=" + this.getEntityState() + ", flag=" + this.getFlag() + "name=" + name + ", typeBillet=" + typeBillet + ", tableGuest=" + tableGuest + ", codeBarre=" + codeBarre + ", present=" + present + ", status=" + status + '}';
    }

    

}
