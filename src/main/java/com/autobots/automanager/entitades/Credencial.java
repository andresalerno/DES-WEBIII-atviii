package com.autobots.automanager.entitades;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credencial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date criacao;

	@Column
	private Date ultimoAcesso;

	@Column(nullable = false)
	private boolean inativo;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Credencial)) return false;
		Credencial that = (Credencial) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
