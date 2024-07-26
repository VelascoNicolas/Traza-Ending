package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query(value = "SELECT * FROM CLIENTE WHERE USER_NAME = :userName", nativeQuery = true)
    public Cliente getCliente(@Param("userName") String userName);
}
