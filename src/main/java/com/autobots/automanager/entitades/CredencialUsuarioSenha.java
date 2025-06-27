package com.autobots.automanager.entitades;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class CredencialUsuarioSenha extends Credencial {
	@Column(nullable = false, unique = true)
	private String nomeUsuario;

	@Column(nullable = false)
	private String senha;
}
