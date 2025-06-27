package com.autobots.automanager.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumeracoes.TipoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoDTO extends RepresentationModel<DocumentoDTO> {
	private Long id;
	private TipoDocumento tipo;
	private String numero;
	private Date dataEmissao;
}
