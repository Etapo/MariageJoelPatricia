/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mariage.joelpatricia.MariageJoelPatriciaApplication;
import com.mariage.joelpatricia.entities.CodeBarre;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Denis ETABA<sylvainonguene@gmail.com>
 */
public class PDFUtils {

    private static final Font BOLD = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD);
    private static final Font BOLD_GRAY = new Font(Font.FontFamily.COURIER, 7, Font.BOLD);
    private static final Font BOLD_DARK_GRAY = new Font(Font.FontFamily.COURIER, 7, Font.BOLD);
    private static final Font BOLD_GREEN = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);
    private static final Font PLAIN = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
    private static final Font PLAIN_BLUE = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
    private static final Font PLAIN_YELLOW = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
    private static final BaseColor COLOR_TITLE = new BaseColor(247, 247, 247); //3, 155, 229

    static {
        BOLD_GRAY.setColor(new BaseColor(200, 200, 200));
        BOLD_DARK_GRAY.setColor(new BaseColor(75, 75, 75));
        BOLD_GREEN.setColor(new BaseColor(50, 155, 60));
        PLAIN_BLUE.setColor(new BaseColor(0, 102, 204));
        PLAIN_YELLOW.setColor(new BaseColor(246, 151, 55));
    }

    /**
     *
     * @param writer
     * @param codeBarre
     * @return
     */
    public static PdfPTable buildCellCode(PdfWriter writer, CodeBarre codeBarre) {

        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(99);
        try {
            table.setTotalWidth(new float[]{99});
        } catch (DocumentException ex) {
            Logger.getLogger(UtilsMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setLockedWidth(true);
        table.setSpacingBefore(0);
        table.setSpacingAfter(0);

        //On créer l'objet cellule.
        PdfPCell cell;

        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(MariageJoelPatriciaApplication.encryption.encryptText(codeBarre.getCodeBarreValue()), 200, 200, null);
        Image codeQrImage = null;
        try {
            codeQrImage = barcodeQRCode.getImage();
            //codeQrImage.scaleAbsolute(512, 512);
            codeQrImage.setBorder(1);
            codeQrImage.setBorderColor(BaseColor.WHITE);
        } catch (BadElementException ex) {
            Logger.getLogger(PDFUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (codeQrImage != null) {
            cell = new PdfPCell(codeQrImage, true);
        } else {
            cell = new PdfPCell(new Phrase("", BOLD_GRAY));
        }

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(60f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(UtilsMethods.completeCode(codeBarre.getSequence()), BOLD_DARK_GRAY));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(16.4f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        return table;
    }

    /**
     *
     * @param writer
     * @param codeBarre
     * @return
     */
    public static PdfPTable addInformationLot(PdfWriter writer, CodeBarre codeBarre) {

        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(109);
        table.setLockedWidth(true);
        table.setSpacingBefore(0);
        table.setSpacingAfter(0);

        //On créer l'objet cellule.
        PdfPCell cell;

        cell = new PdfPCell(addHeadImage(writer));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(23f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Consommez et bénéficier", BOLD_GRAY));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(23f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("#" + UtilsMethods.completeCode(codeBarre.getSequence()), BOLD));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(23f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        return table;
    }

    /**
     *
     * @param writer
     * @return
     */
    public static PdfPTable addHeadImage(PdfWriter writer) {

        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(109);
        try {
            table.setTotalWidth(new float[]{44, 65});
        } catch (DocumentException ex) {
            Logger.getLogger(UtilsMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setLockedWidth(true);
        table.setSpacingBefore(0);
        table.setSpacingAfter(0);

        //On créer l'objet cellule.
        PdfPCell cell;

        Image imageMarketis = null;
        try {
            ClassLoader classLoader = MariageJoelPatriciaApplication.class.getClassLoader();
            URL url = classLoader.getResource("static/assets/img/ic_launcher.png");
            if (url != null) {
                imageMarketis = Image.getInstance(url);
                imageMarketis.setBorder(0);
                imageMarketis.setBorderColor(BaseColor.WHITE);
                imageMarketis.scaleToFit(16, 16);
                imageMarketis.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            }
        } catch (Exception ex) {
            Logger.getLogger(PDFUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (imageMarketis != null) {
            cell = new PdfPCell(imageMarketis, false);
            cell.setPadding(3);
        } else {
            cell = new PdfPCell(new Phrase("", BOLD_GREEN));
        }

        cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell.setFixedHeight(23f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Marketis", BOLD_GREEN));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(23f);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        return table;
    }

}
