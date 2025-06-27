package com.autobots.automanager.dtos;

import java.util.Date;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO extends RepresentationModel<VendaDTO> {
	private Long id;
	private String identificacao;
	private Date cadastro;
	private Set<MercadoriaDTO> mercadorias;
	private Set<ServicoDTO> servicos;
	private VeiculoDTO veiculo;
}
