/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.dto;

import com.mariage.joelpatricia.entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class UserDto extends EntityDto {

    private String name;
    private String surname;
    private String code;
    private String phone;
    private Boolean statut;
    private Boolean canAddTable;
    private Boolean canAddGuest;
    private Boolean canBuildCode;
    private Boolean canCheckGuest;

    public UserDto() {
        super();
    }

    public UserDto(String id) {
        super(id);
    }

    public UserDto(User user) {
        this(user, "dd/MM/yyyy");
    }

    public UserDto(User user, String format) {
        super(user);
        DateFormat dateFormat = new SimpleDateFormat(format);
        this.name = user.getName();
        this.surname = user.getSurname();
        this.code = user.getCode();
        this.phone = user.getPhone();
        this.statut = user.getStatut();

        this.canAddTable = user.getCanAddTable();
        this.canAddGuest = user.getCanAddGuest();
        this.canBuildCode = user.getCanBuildCode();
        this.canCheckGuest = user.getCanCheckGuest();
    }

    public void copyTo(User user) {
        copyTo(user, "dd/MM/yyyy");
    }

    public void copyTo(User user, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setCode(this.code);
        user.setPhone(this.phone);

        user.setCanAddTable(this.canAddTable);
        user.setCanAddGuest(this.canAddGuest);
        user.setCanBuildCode(this.canBuildCode);
        user.setCanCheckGuest(this.canCheckGuest);
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

}
