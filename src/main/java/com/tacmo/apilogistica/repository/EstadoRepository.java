package com.tacmo.apilogistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tacmo.apilogistica.model.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
