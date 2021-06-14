/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.controllers;

import com.mariage.joelpatricia.components.PrintCodeBarreUtils;
import com.mariage.joelpatricia.components.UtilsComponent;
import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.entities.User;
import com.mariage.joelpatricia.repositories.CodeBarreRepository;
import com.mariage.joelpatricia.repositories.UserRepository;
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
    private UtilsComponent utilsComponent;

    @Autowired
    private CodeBarreRepository codeBarreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrintCodeBarreUtils printCodeBarreUtils;

    @RequestMapping(value = {"/init"}, method = RequestMethod.GET)
    public void initBase(HttpServletRequest request, HttpServletResponse response) {

        if (codeBarreRepository.count() == 0) {
            //On génère 500 code barres

            for (int i = 0; i < 600; i++) {
                CodeBarre codeBarre = new CodeBarre();
                UtilsMethods.createID(codeBarre);
                UtilsMethods.loadCreationAttributes(Boolean.TRUE, codeBarre, Boolean.TRUE);
                String codeBarreValue = utilsComponent.buildCodeBarreValue(20);
                codeBarre.setCodeBarreValue(codeBarreValue);
                codeBarre.setStatut(Boolean.FALSE);
                codeBarre.setSequence(i + 1);
                codeBarreRepository.save(codeBarre);
            }
        }

        if (userRepository.count() == 0) {
            //On créé les utilisateurs par défaut

            User user1 = new User();
            UtilsMethods.createID(user1);
            UtilsMethods.loadCreationAttributes(Boolean.TRUE, user1, Boolean.TRUE);
            user1.setName("ETABA ONGUENE");
            user1.setSurname("Denis Sylvain");
            user1.setCode("29051987");
            user1.setPhone("+237679457941");
            user1.setStatut(Boolean.TRUE);
            user1.setCanAddGuest(Boolean.TRUE);
            user1.setCanAddTable(Boolean.TRUE);
            user1.setCanBuildCode(Boolean.FALSE);
            user1.setCanCheckGuest(Boolean.TRUE);
            userRepository.save(user1);

            User user2 = new User();
            UtilsMethods.createID(user2);
            UtilsMethods.loadCreationAttributes(Boolean.TRUE, user2, Boolean.TRUE);
            user2.setName("MANGA MANGA");
            user2.setSurname("Joël Clément");
            user2.setCode("08061985");
            user2.setPhone("+237699338412");
            user2.setStatut(Boolean.TRUE);
            user2.setCanAddGuest(Boolean.TRUE);
            user2.setCanAddTable(Boolean.TRUE);
            user2.setCanBuildCode(Boolean.FALSE);
            user2.setCanCheckGuest(Boolean.TRUE);
            userRepository.save(user2);
        }

    }

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
