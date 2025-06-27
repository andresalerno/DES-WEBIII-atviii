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
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String endereco;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Email)) return false;
		Email email = (Email) o;
		return Objects.equals(id, email.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
