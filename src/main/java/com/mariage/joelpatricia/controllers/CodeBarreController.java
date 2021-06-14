/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.controllers;

import com.mariage.joelpatricia.components.PrintCodeBarreUtils;
import com.mariage.joelpatricia.utils.UtilsMethods;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Denis ETABA<sylvainonguene@gmail.com>
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/codes")
public class CodeBarreController {

    @Autowired
    private PrintCodeBarreUtils printCodeBarreUtils;

    @RequestMapping(value = {"/print"}, method = RequestMethod.GET)
    public void printQRCode(HttpServletRequest request, HttpServletResponse response) {

        //set file name and content type
        String filename = "MARIAGE_JOEL_PATRICIA_BARRE_PRODUCT.pdf";
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        ByteArrayOutputStream outputStream = null;
        byte[] bytes = null;
        try {
            outputStream = new ByteArrayOutputStream();
            printCodeBarreUtils.writeLIstCodeInPdf(outputStream);
            bytes = outputStream.toByteArray();
        } catch (Exception ex) {
            Logger.getLogger(UtilsMethods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //clean off
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException ex) {
                    Logger.getLogger(UtilsMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            if (bytes != null) {
                response.setContentLength(bytes.length);
                FileCopyUtils.copy(bytes, response.getOutputStream());
            }
        } catch (IOException ex) {
            Logger.getLogger(CodeBarreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
