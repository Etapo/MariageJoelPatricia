/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.utils;

import com.mariage.joelpatricia.entities.AbstractEntity;
import java.io.Serializable;

/**
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 * @Project HelpService Core
 * @Since 08/04/2021 - 21:36
 */
public class ServiceMessage implements Serializable {

    private int code;
    private String content;
    private AbstractEntity entity;

    public ServiceMessage() {
    }

    public ServiceMessage(int code) {
        this.code = code;
    }

    public ServiceMessage(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public ServiceMessage(int code, String content, AbstractEntity entity) {
        this.code = code;
        this.content = content;
        this.entity = entity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AbstractEntity getEntity() {
        return entity;
    }

    public void setEntity(AbstractEntity entity) {
        this.entity = entity;
    }

}
