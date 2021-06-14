/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.components;

import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.entities.User;
import com.mariage.joelpatricia.repositories.CodeBarreRepository;
import com.mariage.joelpatricia.repositories.UserRepository;
import com.mariage.joelpatricia.utils.UtilsMethods;
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
    private UtilsComponent utilsComponent;

    @Autowired
    private CodeBarreRepository codeBarreRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

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

}
