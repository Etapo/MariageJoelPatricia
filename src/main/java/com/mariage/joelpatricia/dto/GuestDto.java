/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.dto;

import com.mariage.joelpatricia.entities.Guest;
import com.mariage.joelpatricia.utils.UtilsMethods;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class GuestDto extends EntityDto {

    private String name;
    private String typeBillet;
    private String tableGuestId;
    private String tableGuestName;
    private String codeBarreId;
    private String codeBarreValue;
    private String codeBarreSequence;
    private Boolean present;
    private Boolean status;

    public GuestDto() {
        super();
    }

    public GuestDto(String id) {
        super(id);
    }

    public GuestDto(Guest guest) {
        this(guest, "dd/MM/yyyy");
    }

    public GuestDto(Guest guest, String format) {
        super(guest);
        this.name = guest.getName();
        this.typeBillet = guest.getTypeBillet();
        this.tableGuestId = guest.getTableGuest().getId();
        this.tableGuestName = guest.getTableGuest().getName();
        this.codeBarreId = guest.getCodeBarre().getId();
        this.codeBarreValue = guest.getCodeBarre().getCodeBarreValue();
        this.codeBarreSequence = UtilsMethods.completeCode(guest.getCodeBarre().getSequence());
        this.present = guest.getPresent();
        this.status = guest.getStatus();
    }

    public void copyTo(Guest guest) {
        copyTo(guest, "dd/MM/yyyy");
    }

    public void copyTo(Guest guest, String format) {
        guest.setName(this.name);
        guest.setTypeBillet(this.typeBillet);
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

    public String getTableGuestId() {
        return tableGuestId;
    }

    public void setTableGuestId(String tableGuestId) {
        this.tableGuestId = tableGuestId;
    }

    public String getTableGuestName() {
        return tableGuestName;
    }

    public void setTableGuestName(String tableGuestName) {
        this.tableGuestName = tableGuestName;
    }

    public String getCodeBarreId() {
        return codeBarreId;
    }

    public void setCodeBarreId(String codeBarreId) {
        this.codeBarreId = codeBarreId;
    }

    public String getCodeBarreValue() {
        return codeBarreValue;
    }

    public void setCodeBarreValue(String codeBarreValue) {
        this.codeBarreValue = codeBarreValue;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public String getCodeBarreSequence() {
        return codeBarreSequence;
    }

    public void setCodeBarreSequence(String codeBarreSequence) {
        this.codeBarreSequence = codeBarreSequence;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GuestDto{" + "name=" + name + ", typeBillet=" + typeBillet + ", tableGuestId=" + tableGuestId + ", tableGuestName=" + tableGuestName + ", codeBarreId=" + codeBarreId + ", codeBarreValue=" + codeBarreValue + ", codeBarreSequence=" + codeBarreSequence + ", present=" + present + '}';
    }

}
