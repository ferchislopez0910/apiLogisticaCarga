package com.tacmo.apilogistica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tacmo.apilogistica.model.dto.ActualizarCargaDtoRequest;
import com.tacmo.apilogistica.model.dto.BuscarCargasByFechaCreacionDtoRq;
import com.tacmo.apilogistica.model.dto.CargaDtoRequest;
import com.tacmo.apilogistica.model.entity.Carga;
import com.tacmo.apilogistica.model.entity.Estado;
import com.tacmo.apilogistica.model.entity.TipoCarga;
import com.tacmo.apilogistica.repository.CargaRepository;

@Service
public class TransporteCargaService implements TransporteCargaServiceInterface{
	CargaRepository cargaRepository;
	@Autowired
	public TransporteCargaService(CargaRepository carga) {
		this.cargaRepository = carga;
	}

	@Override
	public Carga createCarga(CargaDtoRequest cargaDto) {
		Carga cargaBd = new Carga();
		Estado estado = new Estado(1, "CREADA");
		TipoCarga tipoCarga = new TipoCarga(cargaDto.getTipoCarga().getId(), cargaDto.getTipoCarga().getNombre());
		
		
		cargaBd.setDestino(   cargaDto.getDestino()   );
		cargaBd.setEstado(estado);
		cargaBd.setPeso( cargaDto.getPeso()  );
		cargaBd.setTipoCarga(tipoCarga);
		
		
		return cargaRepository.save(cargaBd);
		
	}

	@Override
	public List<Carga> listCarga() {
		return cargaRepository.findAll();
	}

	@Override
	public Carga updateCarga(Carga cargaBd, ActualizarCargaDtoRequest cargaRq) {
		Estado estado = new Estado(1, "ACTUALIZADA");
		TipoCarga tipoCarga = new TipoCarga(cargaRq.getTipoCarga().getId(), cargaRq.getTipoCarga().getNombre());
		
		cargaBd.setDestino(   cargaRq.getDestino()   );
		cargaBd.setEstado(estado);
		cargaBd.setPeso( cargaRq.getPeso()  );
		cargaBd.setTipoCarga(tipoCarga);
		
		
		return cargaRepository.save(cargaBd);
	}

	@Override
	public Optional<Carga> listCarga(Integer id) {
		return cargaRepository.findById(id);
	}

	@Override
	public List<Carga> listCargaByFechaCreacion(BuscarCargasByFechaCreacionDtoRq rq) throws Exception {
		//Date createdTimeStart = new SimpleDateFormat("yyyy-MM-dd").parse(rq.getFechaCreacionInicio());
		//Date createdTimeEnd = new SimpleDateFormat("yyyy-MM-dd").parse(rq.getFechaCreacionFin());
		
		return cargaRepository.findByFechaCreacionBetween(rq.getFechaCreacionInicio(), rq.getFechaCreacionFin());
		 
	}

}
