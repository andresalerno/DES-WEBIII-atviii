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
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private double valor;

	@Column
	private String descricao;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Servico)) return false;
		Servico servico = (Servico) o;
		return Objects.equals(id, servico.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
