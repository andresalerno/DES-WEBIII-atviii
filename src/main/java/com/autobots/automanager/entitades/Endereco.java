package com.autobots.automanager.entitades;

import javax.persistence.*;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String estado;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String rua;

	@Column(nullable = false)
	private String numero;

	@Column(nullable = false)
	private String codigoPostal;

	@Column
	private String informacoesAdicionais;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Endereco)) return false;
		Endereco that = (Endereco) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
