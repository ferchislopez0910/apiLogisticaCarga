package com.tacmo.apilogistica.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuscarCargasByFechaCreacionDtoRq {
	private Date fechaCreacionInicio;
	private Date fechaCreacionFin;
}
