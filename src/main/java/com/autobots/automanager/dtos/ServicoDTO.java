package com.autobots.automanager.dtos;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoDTO extends RepresentationModel<ServicoDTO> {
	private Long id;
	private String nome;
	private double valor;
	private String descricao;
}
