package com.autobots.automanager.mappers;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.VeiculoDTO;
import com.autobots.automanager.entitades.Veiculo;

@Component
public class VeiculoMapper {

	public VeiculoDTO toDTO(Veiculo v) {
		if (v == null) return null;

		VeiculoDTO dto = new VeiculoDTO();
		dto.setId(v.getId());
		dto.setModelo(v.getModelo());
		dto.setPlaca(v.getPlaca());
		dto.setTipo(v.getTipo());
		return dto;
	}

	public Veiculo toEntity(VeiculoDTO dto) {
		if (dto == null) return null;

		Veiculo v = new Veiculo();
		v.setId(dto.getId());
		v.setModelo(dto.getModelo());
		v.setPlaca(dto.getPlaca());
		v.setTipo(dto.getTipo());
		return v;
	}
}

