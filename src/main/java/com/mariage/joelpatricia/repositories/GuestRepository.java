/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.repositories;

import com.mariage.joelpatricia.entities.Guest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public interface GuestRepository extends JpaRepository<Guest, String> {

    @Query(value = "SELECT count(g.id) FROM Guest g WHERE g.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public Long getNumberGuestUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    @Query(value = "SELECT g FROM Guest g WHERE g.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public List<Guest> getListGuestUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    @Query(value = "SELECT g FROM Guest g ORDER BY g.name ASC", nativeQuery = false)
    public List<Guest> findGuests();

    @Query(value = "SELECT g FROM Guest g WHERE g.codeBarre.codeBarreValue = :codeBarreValue", nativeQuery = false)
    public Guest findGuestByCodeBarre(@Param("codeBarreValue") String codeBarreValue);

}
