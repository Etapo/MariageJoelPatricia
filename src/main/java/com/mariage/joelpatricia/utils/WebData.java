/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.utils;

import com.mariage.joelpatricia.dto.CodeBarreDto;
import com.mariage.joelpatricia.dto.GuestDto;
import com.mariage.joelpatricia.dto.TableGuestDto;
import com.mariage.joelpatricia.dto.UserDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class WebData implements Serializable {

    private Long operationDate;
    private ServiceMessage message;
    private List<CodeBarreDto> codeBarreDtos = new ArrayList<>();
    private List<TableGuestDto> tableGuestDtos = new ArrayList<>();
    private List<GuestDto> guestDtos = new ArrayList<>();
    private List<UserDto> userDtos = new ArrayList<>();

    public Long getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Long operationDate) {
        this.operationDate = operationDate;
    }

    public ServiceMessage getMessage() {
        return message;
    }

    public void setMessage(ServiceMessage message) {
        this.message = message;
    }

    public List<CodeBarreDto> getCodeBarreDtos() {
        return codeBarreDtos;
    }

    public void setCodeBarreDtos(List<CodeBarreDto> codeBarreDtos) {
        this.codeBarreDtos = codeBarreDtos;
    }

    public List<TableGuestDto> getTableGuestDtos() {
        return tableGuestDtos;
    }

    public void setTableGuestDtos(List<TableGuestDto> tableGuestDtos) {
        this.tableGuestDtos = tableGuestDtos;
    }

    public List<GuestDto> getGuestDtos() {
        return guestDtos;
    }

    public void setGuestDtos(List<GuestDto> guestDtos) {
        this.guestDtos = guestDtos;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

}
