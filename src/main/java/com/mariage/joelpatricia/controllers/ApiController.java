/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.controllers;

import com.mariage.joelpatricia.components.UtilsComponent;
import com.mariage.joelpatricia.dto.GuestDto;
import com.mariage.joelpatricia.dto.TableGuestDto;
import com.mariage.joelpatricia.dto.UserDto;
import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.entities.Guest;
import com.mariage.joelpatricia.entities.TableGuest;
import com.mariage.joelpatricia.entities.User;
import com.mariage.joelpatricia.services.CodeBarreService;
import com.mariage.joelpatricia.services.GuestService;
import com.mariage.joelpatricia.services.TableGuestService;
import com.mariage.joelpatricia.services.UserService;
import com.mariage.joelpatricia.utils.ErrorService;
import com.mariage.joelpatricia.utils.GenericHeader;
import com.mariage.joelpatricia.utils.ServiceMessage;
import com.mariage.joelpatricia.utils.WebData;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UtilsComponent utilsComponent;

    @Autowired
    private TableGuestService tableGuestService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CodeBarreService codeBarreService;

    /**
     * S'authentifier
     * @param genericHeader
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<WebData> authenticate(@RequestBody GenericHeader genericHeader) throws Exception {

        WebData webData = new WebData();
        User user = userService.connect(genericHeader.getEntityId());
        if (user == null) {
            ServiceMessage message = new ServiceMessage(2, "Impossible de s'authetifier, code utilisateur non reconnu");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        webData.getUserDtos().add(new UserDto(user));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);

        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Retourne la liste des données du système
     *
     * @param genericHeader
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<WebData> downloadAllData(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();

        User user = utilsComponent.findUserById(genericHeader.getEntityId());
        if (user != null) {
            if (user.getStatut()) {
                webData.getCodeBarreDtos().addAll(codeBarreService.retrieve());
                webData.getTableGuestDtos().addAll(tableGuestService.retrieve());
                webData.getGuestDtos().addAll(guestService.retrieve());
                webData.getUserDtos().addAll(userService.retrieve());
                ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
                webData.setMessage(message);
            } else {
                ServiceMessage message = new ServiceMessage(1, "Impossible de télécharger les données, votre compte est désactivé");
                webData.setMessage(message);
            }
        } else {
            ServiceMessage message = new ServiceMessage(2, "Impossible de télécharger les données, compte inexistant");
            webData.setMessage(message);
        }

        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Ajout d'un utilisateur
     *
     * @param userDto
     * @return (WebData)
     */
    @RequestMapping(value = "/users/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<WebData> createUser(@RequestBody UserDto userDto) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        ErrorService errorService = new ErrorService();
        utilsComponent.validate(errorService, userDto);
        if (errorService.hasError()) {
            ServiceMessage message = new ServiceMessage(2, "Impossible d'enregistrer cet uilisateur, veuillez-renseigner tous les champs obligatoires");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        User user = userService.create(userDto);
        webData.getUserDtos().add(new UserDto(user));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Modification des informations d'un utilisateur
     *
     * @param userDto
     * @return (WebData)
     */
    @RequestMapping(value = "/users/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<WebData> updateUser(@RequestBody UserDto userDto) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        ErrorService errorService = new ErrorService();
        utilsComponent.validate(errorService, userDto);
        if (errorService.hasError()) {
            ServiceMessage message = new ServiceMessage(2, "Impossible d'enregistrer cet uilisateur, veuillez-renseigner tous les champs obligatoires");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        User user = userService.update(userDto);
        webData.getUserDtos().add(new UserDto(user));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Ajout d'un utilisateur
     *
     * @param tableGuestDto
     * @return (WebData)
     */
    @RequestMapping(value = "/tables/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<WebData> createTable(@RequestBody TableGuestDto tableGuestDto) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        ErrorService errorService = new ErrorService();
        utilsComponent.validate(errorService, tableGuestDto);
        if (errorService.hasError()) {
            ServiceMessage message = new ServiceMessage(2, "Impossible d'enregistrer cette table, veuillez-renseigner tous les champs obligatoires");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        TableGuest tableGuest = tableGuestService.create(tableGuestDto);
        webData.getTableGuestDtos().add(new TableGuestDto(tableGuest));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Modification des informations d'une table
     *
     * @param tableGuestDto
     * @return (WebData)
     */
    @RequestMapping(value = "/tables/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<WebData> updateTable(@RequestBody TableGuestDto tableGuestDto) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        ErrorService errorService = new ErrorService();
        utilsComponent.validate(errorService, tableGuestDto);
        if (errorService.hasError()) {
            ServiceMessage message = new ServiceMessage(2, "Impossible de modifier cette table, veuillez-renseigner tous les champs obligatoires");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        TableGuest tableGuest = tableGuestService.update(tableGuestDto);
        webData.getTableGuestDtos().add(new TableGuestDto(tableGuest));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Supprimer une table
     *
     * @param genericHeader
     * @return (WebData)
     */
    @RequestMapping(value = "/tables/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @javax.transaction.Transactional
    public ResponseEntity<WebData> removeTable(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        TableGuest tableGuest = utilsComponent.findTableGuestById(genericHeader.getEntityId());
        if (tableGuest == null) {
            ServiceMessage message = new ServiceMessage(2, "Error : Table inconnue");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");

        tableGuest = tableGuestService.removeGuest(tableGuest.getId());
        webData.getTableGuestDtos().add(new TableGuestDto(tableGuest));
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Ajout d'un invité
     *
     * @param guestDto
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<WebData> createGuest(@RequestBody GuestDto guestDto) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        ErrorService errorService = new ErrorService();
        utilsComponent.validate(errorService, guestDto);
        if (errorService.hasError()) {
            ServiceMessage message = new ServiceMessage(2, "Impossible d'enregistrer cet invité, veuillez-renseigner tous les champs obligatoires");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        Guest guest = guestService.create(guestDto);
        webData.getGuestDtos().add(new GuestDto(guest));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Modification des informations d'un invité
     *
     * @param guestDto
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<WebData> updateGuest(@RequestBody GuestDto guestDto) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        ErrorService errorService = new ErrorService();
        utilsComponent.validate(errorService, guestDto);
        if (errorService.hasError()) {
            ServiceMessage message = new ServiceMessage(2, "Impossible de modifier cet invité, veuillez-renseigner tous les champs obligatoires");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        Guest guest = guestService.update(guestDto);
        webData.getGuestDtos().add(new GuestDto(guest));
        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Supprimer un invité
     *
     * @param genericHeader
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @javax.transaction.Transactional
    public ResponseEntity<WebData> removeGuest(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        Guest guest = utilsComponent.findGuestById(genericHeader.getEntityId());
        if (guest == null) {
            ServiceMessage message = new ServiceMessage(2, "Error : Invité inconnue");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");

        guest = guestService.removeGuest(guest.getId());
        webData.getGuestDtos().add(new GuestDto(guest));
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Vérifier un code après le scan
     *
     * @param genericHeader
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/check/value", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @javax.transaction.Transactional
    public ResponseEntity<WebData> checkGuestByValue(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        CodeBarre codeBarre = utilsComponent.findCodeBarreByValue(genericHeader.getEntityId());
        if (codeBarre == null) {
            ServiceMessage message = new ServiceMessage(2, "Error : Code barre inconnue");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        } else if (!codeBarre.getStatut()) {
            ServiceMessage message = new ServiceMessage(2, "Error : Code barre non programmé, veuillez consulter l'admin");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");

        Guest guest = utilsComponent.findByCodeBarre(codeBarre);
        webData.getGuestDtos().add(new GuestDto(guest));
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Vérifier un code par séquence
     *
     * @param genericHeader
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/check/sequence", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @javax.transaction.Transactional
    public ResponseEntity<WebData> checkGuestBySequence(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        CodeBarre codeBarre = utilsComponent.findCodeBarreBySequence(genericHeader.getEntityId());
        if (codeBarre == null) {
            ServiceMessage message = new ServiceMessage(2, "Error : Code barre inconnue");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        } else if (!codeBarre.getStatut()) {
            ServiceMessage message = new ServiceMessage(2, "Error : Code barre non programmé, veuillez consulter l'admin");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");

        Guest guest = utilsComponent.findByCodeBarre(codeBarre);
        webData.getGuestDtos().add(new GuestDto(guest));
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Marqué un invité comme présent
     *
     * @param genericHeader
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/mark/present", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @javax.transaction.Transactional
    public ResponseEntity<WebData> markGuestPresent(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        Guest guest = utilsComponent.findGuestById(genericHeader.getEntityId());
        if (guest == null) {
            ServiceMessage message = new ServiceMessage(2, "Error : Invité inconnu");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");

        guest = guestService.markGuestPresent(guest.getId());
        webData.getGuestDtos().add(new GuestDto(guest));
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

    /**
     * Marqué un invité comme absent
     *
     * @param genericHeader
     * @return (WebData)
     */
    @RequestMapping(value = "/guests/mark/absent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @javax.transaction.Transactional
    public ResponseEntity<WebData> markGuestAbsent(@RequestBody GenericHeader genericHeader) {

        WebData webData = new WebData();
        webData.setOperationDate(System.currentTimeMillis());

        Guest guest = utilsComponent.findGuestById(genericHeader.getEntityId());
        if (guest == null) {
            ServiceMessage message = new ServiceMessage(2, "Error : Invité inconnu");
            webData.setMessage(message);
            return new ResponseEntity(webData, HttpStatus.OK);
        }

        ServiceMessage message = new ServiceMessage(0, "Opération effectuée avec succès");

        guest = guestService.markGuestAbsent(guest.getId());
        webData.getGuestDtos().add(new GuestDto(guest));
        webData.setMessage(message);
        return new ResponseEntity(webData, HttpStatus.OK);
    }

}
