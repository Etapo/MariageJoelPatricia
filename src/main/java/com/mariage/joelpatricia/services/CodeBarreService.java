/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.services;

import com.mariage.joelpatricia.dto.CodeBarreDto;
import com.mariage.joelpatricia.repositories.EntityManagerRepositry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Service
public class CodeBarreService {

    @Autowired
    private EntityManagerRepositry entityManagerRepositry;

    /**
     * Liste des utilisateurs
     *
     * @return
     */
    public List<CodeBarreDto> retrieve() {
        return entityManagerRepositry.retrieveAllCodeBarre();
    }

}
