package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.ServicoDTO;
import com.autobots.automanager.entitades.Servico;

public class ServicoMapper {

	public ServicoDTO toDTO(Servico s) {
		if (s == null) return null;

		ServicoDTO dto = new ServicoDTO();
		dto.setId(s.getId());
		dto.setNome(s.getNome());
		dto.setValor(s.getValor());
		dto.setDescricao(s.getDescricao());
		return dto;
	}

	public Servico toEntity(ServicoDTO dto) {
		if (dto == null) return null;

		Servico s = new Servico();
		s.setId(dto.getId());
		s.setNome(dto.getNome());
		s.setValor(dto.getValor());
		s.setDescricao(dto.getDescricao());
		return s;
	}
}
