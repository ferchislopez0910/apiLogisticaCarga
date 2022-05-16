package com.tacmo.apilogistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tacmo.apilogistica.model.entity.TipoCarga;

@Repository
public interface TipoCargaRepository extends JpaRepository<TipoCarga, Integer>{

}
