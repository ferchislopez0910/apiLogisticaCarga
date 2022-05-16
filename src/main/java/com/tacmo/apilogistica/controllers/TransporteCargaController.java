package com.tacmo.apilogistica.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tacmo.apilogistica.common.Utils;
import com.tacmo.apilogistica.model.dto.ActualizarCargaDtoRequest;
import com.tacmo.apilogistica.model.dto.BuscarCargasByFechaCreacionDtoRq;
import com.tacmo.apilogistica.model.dto.CargaDtoRequest;
import com.tacmo.apilogistica.model.dto.CargaDtoResponse;
import com.tacmo.apilogistica.model.entity.Carga;
import com.tacmo.apilogistica.services.TransporteCargaServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/v1/transporteCarga")
public class TransporteCargaController {
	
	@Autowired
	private TransporteCargaServiceInterface cargaService;
	
	@PostMapping()
	@Operation(summary="Crear carga", description="Crear carga en el sistema", tags= {"Carga"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Success - Carga creada",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = Carga.class))}),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
		    @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
	@ResponseBody
	public CargaDtoResponse crearCarga(@Valid @RequestBody CargaDtoRequest carga) {
		Carga cargaDbResponse;
		CargaDtoResponse cargaRs = new CargaDtoResponse();
		
		try {
			cargaDbResponse = cargaService.createCarga(carga);
			
			cargaRs.setId(cargaDbResponse.getId());
			cargaRs.setEstado(cargaDbResponse.getEstado().getNombre());
		} catch (Exception e) {
			cargaRs.setEstado("FALLIDA");
			cargaRs.setRazonError(e.getMessage());
		}

		
		return cargaRs;
		
	}
	
	@GetMapping("/all")
	@Operation(summary="Consultar lista de cargas", description="Consultar lista de cargas registradas en el sistema", tags= {"Carga"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = Carga.class))}),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
		    @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
	@ResponseBody
	public List<Carga> consultarCarga() {
		return cargaService.listCarga();
		
	}
	
	@GetMapping()
	@Operation(summary="Consultar lista de cargas por fecha de creacion", description="Consultar lista de cargas registradas en el sistema", tags= {"Carga"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = Carga.class))}),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
		    @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
	@ResponseBody
	public ResponseEntity consultarCargaByFechaCreacion(
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") final Date fechaCreacionInicio, 
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") final Date fechaCreacionFin) throws Exception {
		
		if(Utils.fechasCorrectasQuery(fechaCreacionInicio, fechaCreacionFin)) {
			BuscarCargasByFechaCreacionDtoRq rq = new BuscarCargasByFechaCreacionDtoRq();
			rq.setFechaCreacionInicio(fechaCreacionInicio);
			rq.setFechaCreacionFin(fechaCreacionFin);
			return new ResponseEntity(cargaService.listCargaByFechaCreacion(rq), HttpStatus.OK);
		}else {
			return new ResponseEntity("fechaCreacionInicio debe ser menor a la fechaCreacionFin", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary="Actualizar carga", description="Actualizar carga registrada en el sistema", tags= {"Carga"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json",
					schema = @Schema(implementation = Carga.class))}),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
		    @ApiResponse(responseCode = "404", description = " Page Not Found", content = @Content)})
	@ResponseBody
	public ResponseEntity actualizarCarga(@PathVariable("id") @Min(1) Integer id, @Valid @RequestBody ActualizarCargaDtoRequest cargaRq) {
		Optional<Carga> datosBd = cargaService.listCarga(id);
		Carga cargaDbResponse;
		CargaDtoResponse cargaRs = new CargaDtoResponse();
		
		if (datosBd.isPresent()) {
			try {
				cargaDbResponse = cargaService.updateCarga(datosBd.get(), cargaRq);
				
				cargaRs.setId(cargaDbResponse.getId());
				cargaRs.setEstado(cargaDbResponse.getEstado().getNombre());
			} catch (Exception e) {
				cargaRs.setEstado("FALLIDA");
				cargaRs.setRazonError(e.getMessage());
			}
		} else {
			cargaRs.setEstado("FALLIDA");
			cargaRs.setRazonError("Carga no encontrada con el id: " + id);
		}

		
		return new ResponseEntity(cargaRs, HttpStatus.OK);
	}

}
