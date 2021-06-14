/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.services;

import com.mariage.joelpatricia.components.UtilsComponent;
import com.mariage.joelpatricia.dto.UserDto;
import com.mariage.joelpatricia.entities.User;
import com.mariage.joelpatricia.repositories.EntityManagerRepositry;
import com.mariage.joelpatricia.repositories.UserRepository;
import com.mariage.joelpatricia.utils.ServiceMessage;
import com.mariage.joelpatricia.utils.UtilsMethods;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Service
public class UserService {

    @Autowired
    private UtilsComponent utilsComponent;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerRepositry entityManagerRepositry;

    /**
     * Liste des utilisateurs
     *
     * @return
     */
    public List<UserDto> retrieve() {
        return entityManagerRepositry.retrieveAllUser();
    }

    /**
     * Connexion d'un utilisateur
     *
     * @param code
     * @return
     */
    public User connect(String code) {
        return userRepository.findByCode(code);
    }

    /**
     * Enregistrement d'un nouvel utilisateur
     *
     * @param userDto
     * @return
     */
    public User create(UserDto userDto) {

        User user = new User();
        UtilsMethods.createID(user);
        UtilsMethods.loadCreationAttributes(Boolean.TRUE, user, Boolean.TRUE);

        userDto.copyTo(user);
        user.setStatut(Boolean.TRUE);
        user = userRepository.save(user);
        return user;
    }

    /**
     * Recherche d'un donateur par téléphone
     *
     * @param phone
     * @return
     */
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    /**
     * Désactivation d'un utilisateur
     *
     * @param donorId
     * @return
     */
    public ServiceMessage disable(String donorId) {
        User user = utilsComponent.findUserById(donorId);
        if (user == null) {
            return new ServiceMessage(2, "L'identifiant de l'utilisateur renseigné n'existe pas");
        }
        user.setStatut(Boolean.FALSE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, user, Boolean.TRUE);
        user = userRepository.save(user);
        return new ServiceMessage(0, "Opération effectuée avec succès", user);
    }

    /**
     * Activation d'un utilisateur
     *
     * @param donorId
     * @return
     */
    public ServiceMessage enable(String donorId) {
        User user = utilsComponent.findUserById(donorId);
        if (user == null) {
            return new ServiceMessage(2, "L'identifiant de l'utilisateur renseigné n'existe pas");
        }
        user.setStatut(Boolean.FALSE);
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, user, Boolean.TRUE);
        user = userRepository.save(user);
        return new ServiceMessage(0, "Opération effectuée avec succès", user);
    }

    /**
     * Mise-à-jour d'un donateur
     *
     * @param userDto
     * @return
     */
    public User update(UserDto userDto) {
        User user = utilsComponent.findUserById(userDto.getId());
        UtilsMethods.loadCreationAttributes(Boolean.FALSE, user, Boolean.TRUE);
        userDto.copyTo(user);
        user = userRepository.save(user);
        return user;
    }

}
