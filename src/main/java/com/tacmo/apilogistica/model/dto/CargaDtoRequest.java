package com.tacmo.apilogistica.model.dto;


import com.tacmo.apilogistica.model.entity.TipoCarga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaDtoRequest {
	
	private float peso;
	private String destino;
	private TipoCarga tipoCarga;
}
