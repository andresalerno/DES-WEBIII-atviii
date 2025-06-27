package com.autobots.automanager.entitades;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.autobots.automanager.enumeracoes.TipoDocumento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private TipoDocumento tipo;

	@Column(nullable = false)
	private Date dataEmissao;

	@Column(unique = true, nullable = false)
	private String numero;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Documento)) return false;
		Documento that = (Documento) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
