package com.autobots.automanager.dtos;

import java.util.Date;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.entitades.Telefone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO extends RepresentationModel<EmpresaDTO> {
	private Long id;
	private String razaoSocial;
	private String nomeFantasia;
	private Date cadastro;
	private Endereco endereco;
	private Set<Telefone> telefones;
}
