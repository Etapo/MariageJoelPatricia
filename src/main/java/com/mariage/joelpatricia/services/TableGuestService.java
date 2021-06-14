/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.services;

import com.mariage.joelpatricia.components.UtilsComponent;
import com.mariage.joelpatricia.dto.TableGuestDto;
import com.mariage.joelpatricia.entities.TableGuest;
import com.mariage.joelpatricia.repositories.EntityManagerRepositry;
import com.mariage.joelpatricia.repositories.TableGuestRepository;
import com.mariage.joelpatricia.utils.UtilsMethods;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Service
public class TableGuestService {

    @Autowired
    private UtilsComponent utilsComponent;

    @Autowired
    private EntityManagerRepositry entityManagerRepositry;

    @Autowired
    private TableGuestRepository tableGuestRepository;

    /**
     * Liste des utilisateurs
     *
     * @return
     */
    public List<TableGuestDto> retrieve() {
        return entityManagerRepositry.retrieveAllTable();
    }

    /**
     * Enregistrement d'une table
     *
     * @param tableGuestDto
     * @return
     */
    public TableGuest create(TableGuestDto tableGuestDto) {

        TableGuest tableGuest = new TableGuest();
        UtilsMethods.createID(tableGuest);
        UtilsMethods.loadCreationAttributes(Boolean.TRUE, tableGuest, Boolean.TRUE);

        tableGuestDto.copyTo(tableGuest);
        tableGuest = tableGuestRepository.save(tableGuest);
        return tableGuest;
    }

    /**
     * Modification d'une table
     *
     * @param tableGuestDto
     * @return
     */
    public TableGuest update(TableGuestDto tableGuestDto) {

        TableGuest tableGuest = utilsComponent.findTableGuestById(tableGuestDto.getId());
        UtilsMethods.createID(tableGuest);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, tableGuest, Boolean.TRUE);

        tableGuestDto.copyTo(tableGuest);
        tableGuest = tableGuestRepository.save(tableGuest);
        return tableGuest;
    }

    /**
     * Suppression d'une table
     *
     * @param tableGuestId
     * @return
     */
    public TableGuest removeGuest(String tableGuestId) {
        TableGuest tableGuest = utilsComponent.findTableGuestById(tableGuestId);
        tableGuestRepository.delete(tableGuest);
        return tableGuest;
    }
}
