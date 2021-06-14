/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.components;

import com.mariage.joelpatricia.MariageJoelPatriciaApplication;
import com.mariage.joelpatricia.dto.GuestDto;
import com.mariage.joelpatricia.dto.TableGuestDto;
import com.mariage.joelpatricia.dto.UserDto;
import com.mariage.joelpatricia.entities.CodeBarre;
import com.mariage.joelpatricia.entities.Guest;
import com.mariage.joelpatricia.entities.TableGuest;
import com.mariage.joelpatricia.entities.User;
import com.mariage.joelpatricia.repositories.CodeBarreRepository;
import com.mariage.joelpatricia.repositories.GuestRepository;
import com.mariage.joelpatricia.repositories.TableGuestRepository;
import com.mariage.joelpatricia.repositories.UserRepository;
import com.mariage.joelpatricia.utils.ErrorService;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Component
public class UtilsComponent {

    @Autowired
    private CodeBarreRepository codeBarreRepository;

    @Autowired
    private TableGuestRepository tableGuestRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private UserRepository userRepository;

    public CodeBarre findCodeBarreById(String id) {
        return codeBarreRepository.findById(id).orElse(null);
    }

    public TableGuest findTableGuestById(String id) {
        return tableGuestRepository.findById(id).orElse(null);
    }

    public Guest findGuestById(String id) {
        return guestRepository.findById(id).orElse(null);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     *
     * @param length
     * @return
     */
    public String buildCodeBarreValue(int length) {
        char[] code = new char[length];
        String finalCode = "";
        Random randomGen = new Random();
        char[] characters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        do {
            for (int i = 0; i < length; i++) {
                code[i] = characters[randomGen.nextInt(characters.length)];
            }
            finalCode = String.valueOf(code);
        } while (codeBarreRepository.findByCodeBarreValue(finalCode) != null);

        return finalCode;
    }

    /**
     *
     * @param errors
     * @param userDto
     */
    public void validate(ErrorService errors, UserDto userDto) {

        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            errors.reject("name", "Le nom de l'utilisateur ne peut être vide");
        }

        if (userDto.getCode() == null || userDto.getCode().isEmpty()) {
            errors.reject("code", "Le code de l'utilisateur ne peut être vide");
        }

        if (userDto.getPhone() == null || userDto.getPhone().isEmpty()) {
            errors.reject("phone", "Le téléphone de l'utilisateur ne peut être vide");
        } else {
            User user = userRepository.findByPhone(userDto.getPhone());
            if (user != null && userDto.getId() != null && !user.getId().equals(userDto.getId())) {
                errors.reject("phone", "Ce numéro de téléphone est déjà utilisé");
            }
        }

    }

    /**
     *
     * @param errors
     * @param tableGuestDto
     */
    public void validate(ErrorService errors, TableGuestDto tableGuestDto) {

        if (tableGuestDto.getName() == null || tableGuestDto.getName().isEmpty()) {
            errors.reject("name", "Le nom de la table ne peut être vide");
        }

        if (tableGuestDto.getNumberPlace() == 0) {
            errors.reject("numberPlace", "Le nombre de places de la table doit être supérieur à 0");
        }

    }

    /**
     *
     * @param errors
     * @param guestDto
     */
    public void validate(ErrorService errors, GuestDto guestDto) {

        if (guestDto.getName() == null || guestDto.getName().isEmpty()) {
            errors.reject("name", "Le nom de la table ne peut être vide");
        }

        if (guestDto.getTypeBillet() == null || guestDto.getTypeBillet().isEmpty()) {
            errors.reject("typeBillet", "Le type de billet ne peut être vide");
        }

        if (guestDto.getId() == null || guestDto.getId().isEmpty()) {
            if ((guestDto.getCodeBarreSequence() == null || guestDto.getCodeBarreSequence().isEmpty()) && (guestDto.getCodeBarreValue() == null || guestDto.getCodeBarreValue().isEmpty())) {
                errors.reject("codeBarre", "Bien-vouloir renseigner le code barre");
            } else {
                CodeBarre codeBarre = null;
                if (guestDto.getCodeBarreValue() != null && !guestDto.getCodeBarreValue().isEmpty()) {
                    String codeBarreValue = MariageJoelPatriciaApplication.encryption.decryptText(guestDto.getCodeBarreValue());
                    codeBarre = codeBarreRepository.findByCodeBarreValue(codeBarreValue);
                } else {
                    int sequence = Integer.valueOf(guestDto.getCodeBarreSequence());
                    codeBarre = codeBarreRepository.findBySequence(sequence);
                }

                if (codeBarre == null) {
                    errors.reject("codeBarre", "Code barre incorrect, bien-vouloir réessayer");
                } else if (codeBarre.getStatut()) {
                    errors.reject("codeBarre", "Code barre déjà utilisé, veuillez utiliser un autre");
                }
            }
        }

    }

    /**
     *
     * @param sequence
     * @return
     */
    public CodeBarre findCodeBarreBySequence(String sequence) {

        return codeBarreRepository.findBySequence(Integer.valueOf(sequence));

    }

    /**
     *
     * @param value
     * @return
     */
    public CodeBarre findCodeBarreByValue(String value) {

        String decryptedValue = MariageJoelPatriciaApplication.encryption.decryptText(value);
        return codeBarreRepository.findByCodeBarreValue(decryptedValue);

    }

    public Guest findByCodeBarre(CodeBarre codeBarre) {
        return guestRepository.findGuestByCodeBarre(codeBarre.getCodeBarreValue());
    }

}
