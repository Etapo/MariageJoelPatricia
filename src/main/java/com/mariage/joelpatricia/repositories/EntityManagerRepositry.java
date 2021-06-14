/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.repositories;

import com.mariage.joelpatricia.dto.CodeBarreDto;
import com.mariage.joelpatricia.dto.GuestDto;
import com.mariage.joelpatricia.dto.TableGuestDto;
import com.mariage.joelpatricia.dto.UserDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
@Repository
public class EntityManagerRepositry {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @return
     */
    public List<TableGuestDto> retrieveAllTable() {
        String queryString = "SELECT new com.mariage.joelpatricia.dto.TableGuestDto(t, ( SELECT count(g.id) FROM Guest g WHERE g.present IS TRUE AND g.tableGuest.id = t.id ) ) FROM TableGuest t ORDER BY t.name ASC";
        Query query = entityManager.createQuery(queryString, TableGuestDto.class);
        return query.getResultList();
    }

    /**
     *
     * @return
     */
    public List<GuestDto> retrieveAllGuests() {
        String queryString = "SELECT new com.mariage.joelpatricia.dto.GuestDto(g) FROM Guest g ";
        Query query = entityManager.createQuery(queryString, GuestDto.class);
        return query.getResultList();
    }
    
    /**
     *
     * @return
     */
    public List<CodeBarreDto> retrieveAllCodeBarre() {
        String queryString = "SELECT new com.mariage.joelpatricia.dto.CodeBarreDto(c) FROM CodeBarre c ";
        Query query = entityManager.createQuery(queryString, CodeBarreDto.class);
        return query.getResultList();
    }
    
     /**
     *
     * @return
     */
    public List<UserDto> retrieveAllUser() {
        String queryString = "SELECT new com.mariage.joelpatricia.dto.UserDto(u) FROM User u ";
        Query query = entityManager.createQuery(queryString, UserDto.class);
        return query.getResultList();
    }

}
