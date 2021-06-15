/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.components;

import com.mariage.joelpatricia.MariageJoelPatriciaApplication;
import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.repositories.CodeBarreRepository;
import com.mariage.joelpatricia.utils.UtilsMethods;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Component
public class Initializer implements ApplicationRunner {

    @Autowired
    private CodeBarreRepository codeBarreRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (codeBarreRepository.count() != 0) {
            List<CodeBarre> codeBarres = codeBarreRepository.findAll();
            for (CodeBarre codeBarre : codeBarres) {

                if (codeBarre.getCodeBarreEncrypted() == null) {
                    codeBarre.setCodeBarreEncrypted(MariageJoelPatriciaApplication.encryption.encryptText(codeBarre.getCodeBarreValue()));
                    UtilsMethods.loadCreationAttributes(Boolean.FALSE, codeBarre, Boolean.TRUE);
                    codeBarreRepository.save(codeBarre);
                }
            }
        }

    }

}
