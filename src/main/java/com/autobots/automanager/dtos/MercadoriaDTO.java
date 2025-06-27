package com.autobots.automanager.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MercadoriaDTO extends RepresentationModel<MercadoriaDTO> {
	private Long id;
	private String nome;
	private long quantidade;
	private double valor;
	private Date cadastro;
	private Date validade;
	private Date fabricacao;
	private String descricao;
}
