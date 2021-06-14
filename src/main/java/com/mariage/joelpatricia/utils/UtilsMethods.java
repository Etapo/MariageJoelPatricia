/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.utils;

import com.mariage.joelpatricia.entities.AbstractEntity;
import com.mariage.joelpatricia.entities.User;
import java.util.Random;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class UtilsMethods {

    /**
     *
     * @param entity
     */
    public static void createID(AbstractEntity entity) {
        if (entity.getId() == null || entity.getId().isEmpty()) {
            String id = AutoGenerateBuild.generateId(entity.getClass());
            entity.setId(id);
        }
    }

    /**
     *
     * @param creation
     * @param entity
     * @param entityStatut
     */
    public static void loadCreationAttributes(Boolean creation, AbstractEntity entity, Boolean entityStatut) {

        entity.setEntityState(entityStatut);
        entity.setLastDateUpdate(System.currentTimeMillis());
        if (creation) {
            entity.setFlag(Boolean.TRUE);
            entity.setDateCreation(System.currentTimeMillis());
        }
        entity.setUserCreation("SYSTEM_IN");
        entity.setLastUserUpdate("SYSTEM_IN");
    }

    /**
     *
     * @param creation
     * @param entity
     * @param user
     * @param entityStatut
     */
    public static void loadCreationAttributes(Boolean creation, AbstractEntity entity, User user, boolean entityStatut) {

        entity.setEntityState(entityStatut);
        entity.setLastDateUpdate(System.currentTimeMillis());
        if (creation) {
            entity.setDateCreation(System.currentTimeMillis());
            entity.setUserCreation(user.getId());
        }
        entity.setLastUserUpdate(user.getId());
    }

    /**
     *
     * @param codeBarre
     * @return
     */
    public static String completeCode(Integer codeBarre) {
        if (codeBarre > 0 && codeBarre < 10) {
            return "0000000" + codeBarre;
        } else if (codeBarre >= 10 && codeBarre < 100) {
            return "000000" + codeBarre;
        } else if (codeBarre >= 100 && codeBarre < 1000) {
            return "00000" + codeBarre;
        } else if (codeBarre >= 1000 && codeBarre < 10000) {
            return "0000" + codeBarre;
        } else if (codeBarre >= 10000 && codeBarre < 100000) {
            return "000" + codeBarre;
        } else if (codeBarre >= 100000 && codeBarre < 1000000) {
            return "00" + codeBarre;
        } else if (codeBarre >= 1000000 && codeBarre < 10000000) {
            return "0" + codeBarre;
        } else {
            return "" + codeBarre;
        }
    }

}
