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
@Table(name = "mariage_joel_patricia_user")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 190)
    @Column(name = "name", length = 190, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 190)
    @Column(name = "surname", length = 190, nullable = false)
    private String surname;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phone", length = 50, nullable = false)
    private String phone;

    @Column(name = "statut")
    private Boolean statut;

    @Column(name = "can_add_table")
    private Boolean canAddTable;

    @Column(name = "can_add_guest")
    private Boolean canAddGuest;

    @Column(name = "can_build_code")
    private Boolean canBuildCode;

    @Column(name = "can_check_guest")
    private Boolean canCheckGuest;

    public User() {
    }

    public User(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Boolean getCanAddTable() {
        return canAddTable;
    }

    public void setCanAddTable(Boolean canAddTable) {
        this.canAddTable = canAddTable;
    }

    public Boolean getCanAddGuest() {
        return canAddGuest;
    }

    public void setCanAddGuest(Boolean canAddGuest) {
        this.canAddGuest = canAddGuest;
    }

    public Boolean getCanBuildCode() {
        return canBuildCode;
    }

    public void setCanBuildCode(Boolean canBuildCode) {
        this.canBuildCode = canBuildCode;
    }

    public Boolean getCanCheckGuest() {
        return canCheckGuest;
    }

    public void setCanCheckGuest(Boolean canCheckGuest) {
        this.canCheckGuest = canCheckGuest;
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
        final User other = (User) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.getId() + ", dateCreation=" + this.getDateCreation() + ", lastDateUpdate=" + this.getLastDateUpdate() + ", userCreation=" + this.getUserCreation() + ", lastUserUpdate=" + this.getLastUserUpdate() + ", entityState=" + this.getEntityState() + ", flag=" + this.getFlag() + "name=" + name + ", surname=" + surname + ", code=" + code + ", phone=" + phone + ", statut=" + statut + ", canAddTable=" + canAddTable + ", canAddGuest=" + canAddGuest + ", canBuildCode=" + canBuildCode + ", canCheckGuest=" + canCheckGuest + '}';
    }

}
