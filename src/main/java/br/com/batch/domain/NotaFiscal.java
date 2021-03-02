package br.com.batch.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class NotaFiscal {
	private Integer numero;
	private BigDecimal valor;
	private String tomador;
}
