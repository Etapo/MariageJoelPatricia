/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.dto;

import com.mariage.joelpatricia.entities.TableGuest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class TableGuestDto extends EntityDto {

    private String name;
    private Integer numberPlace;
    private Long numberPlaceOccuped;

    public TableGuestDto() {
        super();
    }

    public TableGuestDto(String id) {
        super(id);
    }

    public TableGuestDto(TableGuest tableGuest) {
        super(tableGuest);
        this.name = tableGuest.getName();
        this.numberPlace = tableGuest.getNumberPlace();
        this.numberPlaceOccuped = 0L;
    }

    public TableGuestDto(TableGuest tableGuest, Long numberPlaceOccuped) {
        super(tableGuest);
        this.name = tableGuest.getName();
        this.numberPlace = tableGuest.getNumberPlace();
        this.numberPlaceOccuped = numberPlaceOccuped;
    }

    public TableGuestDto(TableGuest tableGuest, String format) {
        super(tableGuest);
        DateFormat dateFormat = new SimpleDateFormat(format);
        this.name = tableGuest.getName();
        this.numberPlace = tableGuest.getNumberPlace();
        this.numberPlaceOccuped = 0L;
    }

    public void copyTo(TableGuest tableGuest) {
        copyTo(tableGuest, "dd/MM/yyyy");
    }

    public void copyTo(TableGuest tableGuest, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        tableGuest.setName(this.name);
        tableGuest.setNumberPlace(this.numberPlace);
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

    public Long getNumberPlaceOccuped() {
        return numberPlaceOccuped;
    }

    public void setNumberPlaceOccuped(Long numberPlaceOccuped) {
        this.numberPlaceOccuped = numberPlaceOccuped;
    }

    @Override
    public String toString() {
        return "TableGuestDto{" + "name=" + name + ", numberPlace=" + numberPlace + ", numberPlaceOccuped=" + numberPlaceOccuped + '}';
    }

}
