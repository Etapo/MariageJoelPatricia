/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.repositories;

import com.mariage.joelpatricia.entities.TableGuest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public interface TableGuestRepository extends JpaRepository<TableGuest, String> {

    @Query(value = "SELECT count(t.id) FROM TableGuest t WHERE t.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public Long getNumberTableGuestUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    @Query(value = "SELECT t FROM TableGuest t WHERE t.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public List<TableGuest> getListTableGuestUpdated(@Param("lastDateUpdate") long lastDateUpdate);

}
