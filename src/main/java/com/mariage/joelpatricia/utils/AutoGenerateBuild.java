/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public class AutoGenerateBuild {

    private static final String[] entities = {
        "CodeBarre",
        "TableGuest",
        "Guest",
        "User"
    };
    private static final Integer[] codes = {61, 15, 49, 21};

    public static String generateId(Class class1) {

        long time = System.currentTimeMillis();
        String address = "127.0.0.1";
        int codeClass = getCodeClass(class1);

        try {
            address = InetAddress.getByName("localhost").getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(AutoGenerateBuild.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            address = address.replace(".", "");
            long addressValue = Long.valueOf(address);
            address = (addressValue * codeClass) + "";
        } catch (NumberFormatException ex) {
        }

        Random random = new Random();
        int randomValue = random.nextInt(100000000);

        return time + address + randomValue;
    }

    private static int getCodeClass(Class clazz) {
        int position = -1;
        for (int i = 0; i < entities.length; i++) {
            if (entities[i].equalsIgnoreCase(clazz.getName())) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            return 18;
        } else {
            return codes[position];
        }
    }
}
