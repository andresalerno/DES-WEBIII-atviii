package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.MercadoriaDTO;
import com.autobots.automanager.entitades.Mercadoria;

public class MercadoriaMapper {

	public MercadoriaDTO toDTO(Mercadoria m) {
		if (m == null) return null;

		MercadoriaDTO dto = new MercadoriaDTO();
		dto.setId(m.getId());
		dto.setNome(m.getNome());
		dto.setQuantidade(m.getQuantidade());
		dto.setValor(m.getValor());
		dto.setCadastro(m.getCadastro());
		dto.setValidade(m.getValidade());
		dto.setFabricacao(m.getFabricao()); // Corrija se o nome do campo foi ajustado
		dto.setDescricao(m.getDescricao());
		return dto;
	}

	public Mercadoria toEntity(MercadoriaDTO dto) {
		if (dto == null) return null;

		Mercadoria m = new Mercadoria();
		m.setId(dto.getId());
		m.setNome(dto.getNome());
		m.setQuantidade(dto.getQuantidade());
		m.setValor(dto.getValor());
		m.setCadastro(dto.getCadastro());
		m.setValidade(dto.getValidade());
		m.setFabricao(dto.getFabricacao()); // Corrija se necessário
		m.setDescricao(dto.getDescricao());
		return m;
	}
}
