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
@Table(name = "mariage_joel_patricia_code_barre")
public class CodeBarre extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "sequence", nullable = false, unique = true)
    private Integer sequence;

    @NotNull
    @Size(min = 10, max = 100)
    @Column(name = "code_barre_value", length = 100, nullable = false)
    private String codeBarreValue;

    @Column(name = "statut")
    private Boolean statut;

    @Size(min = 10, max = 190)
    @Column(name = "code_barre_encrypted", length = 100, nullable = true)
    private String codeBarreEncrypted;

    public CodeBarre() {
    }

    public CodeBarre(String id) {
        super(id);
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getCodeBarreValue() {
        return codeBarreValue;
    }

    public void setCodeBarreValue(String codeBarreValue) {
        this.codeBarreValue = codeBarreValue;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public String getCodeBarreEncrypted() {
        return codeBarreEncrypted;
    }

    public void setCodeBarreEncrypted(String codeBarreEncrypted) {
        this.codeBarreEncrypted = codeBarreEncrypted;
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
        final CodeBarre other = (CodeBarre) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "CodeBarre{" + "id=" + this.getId() + ", dateCreation=" + this.getDateCreation() + ", lastDateUpdate=" + this.getLastDateUpdate() + ", userCreation=" + this.getUserCreation() + ", lastUserUpdate=" + this.getLastUserUpdate() + ", entityState=" + this.getEntityState() + ", flag=" + this.getFlag() + "sequence=" + sequence + ", codeBarreValue=" + codeBarreValue + ", statut=" + statut + '}';
    }

}
