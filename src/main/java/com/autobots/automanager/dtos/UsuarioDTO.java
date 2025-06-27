package com.autobots.automanager.dtos;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.enumeracoes.PerfilUsuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {
	private Long id;
	private String nome;
	private String nomeSocial;
	private Set<PerfilUsuario> perfis;
	private Set<Telefone> telefones;
	private Endereco endereco;
	private Set<Documento> documentos;
	private Set<Email> emails;
	private Long empresaId;

}
