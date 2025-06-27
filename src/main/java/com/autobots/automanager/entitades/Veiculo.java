package com.autobots.automanager.entitades;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "proprietario", "vendas" })
@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private TipoVeiculo tipo;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false)
	private String placa;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Usuario proprietario;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Venda> vendas = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Veiculo)) return false;
		Veiculo veiculo = (Veiculo) o;
		return Objects.equals(id, veiculo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
