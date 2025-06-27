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
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String ddd;

	@Column(nullable = false)
	private String numero;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Telefone)) return false;
		Telefone telefone = (Telefone) o;
		return Objects.equals(id, telefone.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
