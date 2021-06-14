/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.dto;

import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.utils.UtilsMethods;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class CodeBarreDto extends EntityDto {

    private String sequence;
    private String codeBarreValue;
    private Boolean statut;

    public CodeBarreDto() {
        super();
    }

    public CodeBarreDto(String id) {
        super(id);
    }

    public CodeBarreDto(CodeBarre codeBarre) {
        this(codeBarre, "dd/MM/yyyy");
    }

    public CodeBarreDto(CodeBarre codeBarre, String format) {
        super(codeBarre);
        DateFormat dateFormat = new SimpleDateFormat(format);
        this.sequence = UtilsMethods.completeCode(codeBarre.getSequence());
        this.codeBarreValue = codeBarre.getCodeBarreValue();
        this.statut = codeBarre.getStatut();
    }

    public void copyTo(CodeBarre codeBarre) {
        copyTo(codeBarre, "dd/MM/yyyy");
    }

    public void copyTo(CodeBarre codeBarre, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        codeBarre.setSequence(Integer.valueOf(this.sequence));
        codeBarre.setCodeBarreValue(this.codeBarreValue);
        codeBarre.setStatut(this.statut);
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
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

    @Override
    public String toString() {
        return "CodeBarreDto{" + "sequence=" + sequence + ", codeBarreValue=" + codeBarreValue + ", statut=" + statut + '}';
    }

}
