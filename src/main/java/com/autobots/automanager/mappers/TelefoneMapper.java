package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.TelefoneDTO;
import com.autobots.automanager.entitades.Telefone;

public class TelefoneMapper {

	public TelefoneDTO toDTO(Telefone tel) {
		if (tel == null) return null;

		TelefoneDTO dto = new TelefoneDTO();
		dto.setId(tel.getId());
		dto.setDdd(tel.getDdd());
		dto.setNumero(tel.getNumero());
		return dto;
	}

	public Telefone toEntity(TelefoneDTO dto) {
		if (dto == null) return null;

		Telefone tel = new Telefone();
		tel.setId(dto.getId());
		tel.setDdd(dto.getDdd());
		tel.setNumero(dto.getNumero());
		return tel;
	}
}
