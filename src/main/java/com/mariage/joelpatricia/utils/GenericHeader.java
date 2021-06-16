/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.utils;

import java.io.Serializable;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class GenericHeader implements Serializable {

    private String entityId;
    private Long lastDateUpdate;
    private Boolean downloadCode;

    public GenericHeader() {
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Long getLastDateUpdate() {
        return lastDateUpdate;
    }

    public void setLastDateUpdate(Long lastDateUpdate) {
        this.lastDateUpdate = lastDateUpdate;
    }

    public Boolean getDownloadCode() {
        return downloadCode;
    }

    public void setDownloadCode(Boolean downloadCode) {
        this.downloadCode = downloadCode;
    }

    @Override
    public String toString() {
        return "GenericHeader{" + "entityId=" + entityId + ", lastDateUpdate=" + lastDateUpdate + ", downloadCode=" + downloadCode + '}';
    }

}
