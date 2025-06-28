package com.autobots.automanager.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.VendaDTO;
import com.autobots.automanager.entitades.Venda;

@Component
public class VendaMapper {

	private final MercadoriaMapper mercadoriaMapper = new MercadoriaMapper();
	private final ServicoMapper servicoMapper = new ServicoMapper();
	private final VeiculoMapper veiculoMapper = new VeiculoMapper();

	public VendaDTO toDTO(Venda v) {
		if (v == null) return null;

		VendaDTO dto = new VendaDTO();
		dto.setId(v.getId());
		dto.setIdentificacao(v.getIdentificacao());
		dto.setCadastro(v.getCadastro());
		dto.setMercadorias(
			v.getMercadorias().stream().map(mercadoriaMapper::toDTO).collect(Collectors.toSet())
		);
		dto.setServicos(
			v.getServicos().stream().map(servicoMapper::toDTO).collect(Collectors.toSet())
		);
		dto.setVeiculo(veiculoMapper.toDTO(v.getVeiculo()));
		return dto;
	}

	public Venda toEntity(VendaDTO dto) {
		if (dto == null) return null;

		Venda v = new Venda();
		v.setId(dto.getId());
		v.setIdentificacao(dto.getIdentificacao());
		v.setCadastro(dto.getCadastro());
		v.setMercadorias(
			dto.getMercadorias().stream().map(mercadoriaMapper::toEntity).collect(Collectors.toSet())
		);
		v.setServicos(
			dto.getServicos().stream().map(servicoMapper::toEntity).collect(Collectors.toSet())
		);
		v.setVeiculo(veiculoMapper.toEntity(dto.getVeiculo()));
		return v;
	}
}
