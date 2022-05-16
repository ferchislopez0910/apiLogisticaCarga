package com.tacmo.apilogistica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaDtoResponse {
	private Integer id;
	private String estado;
	private String razonError;
}
