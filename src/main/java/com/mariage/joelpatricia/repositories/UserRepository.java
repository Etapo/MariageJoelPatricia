/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mariage.joelpatricia.repositories;

import com.mariage.joelpatricia.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author <a href="mailto:sylvainonguene@gmail.com">Denis ETABA</a>
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT count(u.id) FROM User u WHERE u.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public Long getNumberUserUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    @Query(value = "SELECT u FROM User u WHERE u.lastDateUpdate > :lastDateUpdate", nativeQuery = false)
    public List<User> getListUserUpdated(@Param("lastDateUpdate") long lastDateUpdate);

    @Query(value = "SELECT u FROM User u ORDER BY u.name ASC, u.surname ASC", nativeQuery = false)
    public List<User> findUsers();

    public User findByCode(String code);

    public User findByPhone(String phone);

}
