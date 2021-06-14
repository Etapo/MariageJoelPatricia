/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.components;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.repositories.CodeBarreRepository;
import com.mariage.joelpatricia.utils.DottedCell;
import com.mariage.joelpatricia.utils.PDFUtils;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Denis ETABA<sylvainonguene@gmail.com>
 */
@Component
public class PrintCodeBarreUtils {
    
    @Autowired
    private CodeBarreRepository codeBarreRepository;
    
    public void writeLIstCodeInPdf(OutputStream outputStream) throws Exception {
        
        List<CodeBarre> codeBarres = codeBarreRepository.findCodeBarres();
        
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
        document.setMargins(0f, 0f, 0f, 0f);
        document.open();
        
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(594);
        try {
            table.setTotalWidth(new float[]{99, 99, 99, 99, 99, 99});
        } catch (DocumentException ex) {
            Logger.getLogger(PrintCodeBarreUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setLockedWidth(true);
        table.setSpacingBefore(0);
        table.setSpacingAfter(0);
        
        for (CodeBarre codeBarre : codeBarres) {
            //On créer l'objet cellule.
            PdfPCell cell1 = new PdfPCell(PDFUtils.buildCellCode(pdfWriter, codeBarre));
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setFixedHeight(76.4f);
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setCellEvent(new DottedCell(PdfPCell.BOTTOM | PdfPCell.RIGHT));
            table.addCell(cell1);
        }
        
        //On complète les cases manquantes dans la ligne
        if (codeBarres.size() % 6 != 0) {
            int index = codeBarres.size() / 6;
            int start = index * 6 + 1;
            int end = index * 6 + 6;
            for (int i = start; i < end; i++) {
                PdfPCell cell1 = new PdfPCell(new Phrase(""));
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setFixedHeight(76.4f);
                cell1.setBorder(PdfPCell.NO_BORDER);
                cell1.setCellEvent(new DottedCell(PdfPCell.BOTTOM | PdfPCell.RIGHT));
                table.addCell(cell1);
            }
        }
        
        document.add(table);
        document.close();
    }
    
}
