package com.tacmo.apilogistica.repository;

import org.springframework.stereotype.Repository;

import com.tacmo.apilogistica.model.entity.Carga;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CargaRepository extends JpaRepository<Carga, Integer> {
	//@Query("select a from Carga a where a.fechaCreacion <= :fechaCreacion")
	List<Carga> findByFechaCreacionBetween(Date createdTimeStart,Date createdTimeEnd);
	
}


