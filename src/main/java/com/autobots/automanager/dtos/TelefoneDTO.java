package com.autobots.automanager.dtos;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDTO extends RepresentationModel<TelefoneDTO> {
	private Long id;
	private String ddd;
	private String numero;
}
