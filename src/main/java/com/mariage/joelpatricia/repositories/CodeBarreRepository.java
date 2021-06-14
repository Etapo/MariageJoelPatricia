/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.repositories;

import com.mariage.joelpatricia.entities.CodeBarre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public interface CodeBarreRepository extends JpaRepository<CodeBarre, String> {

    @Query(value = "SELECT count(c.id) FROM CodeBarre c WHERE c.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public Long getNumberCodeBarreUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    @Query(value = "SELECT c FROM CodeBarre c WHERE c.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public List<CodeBarre> getListCodeBarreUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    public CodeBarre findByCodeBarreValue(String codeBarreValue);
    
    public CodeBarre findBySequence(int sequence);

    @Query(value = "SELECT c FROM CodeBarre c ORDER BY c.sequence ASC", nativeQuery = false)
    public List<CodeBarre> findCodeBarres();

}
