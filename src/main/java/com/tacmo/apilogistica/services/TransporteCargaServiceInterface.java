package com.tacmo.apilogistica.services;


import java.util.List;
import java.util.Optional;

import com.tacmo.apilogistica.model.dto.ActualizarCargaDtoRequest;
import com.tacmo.apilogistica.model.dto.BuscarCargasByFechaCreacionDtoRq;
import com.tacmo.apilogistica.model.dto.CargaDtoRequest;
import com.tacmo.apilogistica.model.entity.Carga;

public interface TransporteCargaServiceInterface {
	
	Carga createCarga(CargaDtoRequest carga);
	List<Carga> listCarga();
	List<Carga> listCargaByFechaCreacion(BuscarCargasByFechaCreacionDtoRq rq) throws Exception;
	Optional<Carga> listCarga(Integer id);
	Carga updateCarga(Carga cargaBd,ActualizarCargaDtoRequest cargaRq);
}
