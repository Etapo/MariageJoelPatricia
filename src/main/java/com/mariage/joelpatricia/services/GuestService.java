/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.services;

import com.mariage.joelpatricia.MariageJoelPatriciaApplication;
import com.mariage.joelpatricia.components.UtilsComponent;
import com.mariage.joelpatricia.dto.GuestDto;
import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.entities.Guest;
import com.mariage.joelpatricia.repositories.CodeBarreRepository;
import com.mariage.joelpatricia.repositories.EntityManagerRepositry;
import com.mariage.joelpatricia.repositories.GuestRepository;
import com.mariage.joelpatricia.utils.UtilsMethods;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Service
public class GuestService {

    @Autowired
    private UtilsComponent utilsComponent;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private CodeBarreRepository codeBarreRepository;

    @Autowired
    private EntityManagerRepositry entityManagerRepositry;

    /**
     * Liste des utilisateurs
     *
     * @return
     */
    public List<GuestDto> retrieve() {
        return entityManagerRepositry.retrieveAllGuests();
    }

    /**
     * Retrieve One Guest by code barre
     *
     * @param codeBarreValue
     * @return
     */
    public Guest retrieve(String codeBarreValue) {
        return guestRepository.findGuestByCodeBarre(codeBarreValue);
    }

    /**
     * Enregistrement d'un nouvel invité
     *
     * @param guestDto
     * @return
     */
    public Guest create(GuestDto guestDto) {

        Guest guest = new Guest();
        UtilsMethods.createID(guest);
        UtilsMethods.loadCreationAttributes(Boolean.TRUE, guest, Boolean.TRUE);

        guestDto.copyTo(guest);

        CodeBarre codeBarre;
        guest.setPresent(Boolean.FALSE);
        guest.setStatus(Boolean.FALSE);
        if (guestDto.getCodeBarreValue() != null && !guestDto.getCodeBarreValue().isEmpty()) {
            String codeBarreValue = MariageJoelPatriciaApplication.encryption.decryptText(guestDto.getCodeBarreValue());
            codeBarre = codeBarreRepository.findByCodeBarreValue(codeBarreValue);
        } else {
            int sequence = Integer.valueOf(guestDto.getCodeBarreSequence());
            codeBarre = codeBarreRepository.findBySequence(sequence);
        }

        guest.setCodeBarre(codeBarre);
        guest.setTableGuest(utilsComponent.findTableGuestById(guestDto.getTableGuestId()));
        guest = guestRepository.save(guest);

        //On met à jour le code barre
        codeBarre.setStatut(Boolean.TRUE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, codeBarre, Boolean.TRUE);
        codeBarreRepository.save(codeBarre);
        return guest;
    }

    /**
     * Modification d'un invité
     *
     * @param guestDto
     * @return
     */
    public Guest update(GuestDto guestDto) {

        Guest guest = utilsComponent.findGuestById(guestDto.getId());
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, guest, Boolean.TRUE);
        guestDto.copyTo(guest);
        guest.setTableGuest(utilsComponent.findTableGuestById(guestDto.getTableGuestId()));
        guest = guestRepository.save(guest);
        return guest;
    }

    /**
     * Suppression d'un invité
     *
     * @param guestId
     * @return
     */
    public Guest removeGuest(String guestId) {

        Guest guest = utilsComponent.findGuestById(guestId);
        CodeBarre codeBarre = guest.getCodeBarre();
        guestRepository.delete(guest);

        //On met à jour le code barre                
        codeBarre.setStatut(Boolean.FALSE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, codeBarre, Boolean.TRUE);
        codeBarreRepository.save(codeBarre);

        return guest;
    }

    /**
     * Marqué un invité comme présent
     *
     * @param guestId
     * @return
     */
    public Guest markGuestPresent(String guestId) {
        Guest guest = utilsComponent.findGuestById(guestId);
        guest.setPresent(Boolean.TRUE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, guest, Boolean.TRUE);
        guest = guestRepository.save(guest);
        return guest;
    }

    /**
     * Marqué un invité comme présent
     *
     * @param guestId
     * @return
     */
    public Guest markGuestAbsent(String guestId) {
        Guest guest = utilsComponent.findGuestById(guestId);
        guest.setPresent(Boolean.FALSE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, guest, Boolean.TRUE);
        guest = guestRepository.save(guest);
        return guest;
    }
    
    /**
     * Remettre le billet physiquement à l'invité
     *
     * @param guestId
     * @return
     */
    public Guest handOverGuestTicket(String guestId) {
        Guest guest = utilsComponent.findGuestById(guestId);
        guest.setStatus(Boolean.TRUE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, guest, Boolean.TRUE);
        guest = guestRepository.save(guest);
        return guest;
    }

}
