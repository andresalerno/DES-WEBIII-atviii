package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.EmpresaDTO;
import com.autobots.automanager.entitades.Empresa;

public class EmpresaMapper {

	public EmpresaDTO toDTO(Empresa empresa) {
		if (empresa == null) return null;

		EmpresaDTO dto = new EmpresaDTO();
		dto.setId(empresa.getId());
		dto.setRazaoSocial(empresa.getRazaoSocial());
		dto.setNomeFantasia(empresa.getNomeFantasia());
		dto.setCadastro(empresa.getCadastro());
		dto.setEndereco(empresa.getEndereco());
		dto.setTelefones(empresa.getTelefones());
		return dto;
	}

	public Empresa toEntity(EmpresaDTO dto) {
		if (dto == null) return null;

		Empresa empresa = new Empresa();
		empresa.setId(dto.getId());
		empresa.setRazaoSocial(dto.getRazaoSocial());
		empresa.setNomeFantasia(dto.getNomeFantasia());
		empresa.setCadastro(dto.getCadastro());
		empresa.setEndereco(dto.getEndereco());
		empresa.setTelefones(dto.getTelefones());
		return empresa;
	}
}
