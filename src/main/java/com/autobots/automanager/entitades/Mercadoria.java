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
public class Mercadoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date validade;

	@Column(nullable = false)
	private Date fabricao;

	@Column(nullable = false)
	private Date cadastro;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private long quantidade;

	@Column(nullable = false)
	private double valor;

	@Column
	private String descricao;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Mercadoria)) return false;
		Mercadoria that = (Mercadoria) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
