package com.autobots.automanager.mappers;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entitades.Usuario;

@Component
public class UsuarioMapper {

	public UsuarioDTO toDTO(Usuario usuario) {
		if (usuario == null) return null;

		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setNomeSocial(usuario.getNomeSocial());
		dto.setPerfis(usuario.getPerfis());
		dto.setTelefones(usuario.getTelefones());
		dto.setEndereco(usuario.getEndereco());
		dto.setDocumentos(usuario.getDocumentos());
		dto.setEmails(usuario.getEmails());

		// Se quiser retornar apenas o ID da empresa associada:
		if (usuario.getEmpresa() != null) {
			dto.setEmpresaId(usuario.getEmpresa().getId());
		}

		return dto;
	}

	public Usuario toEntity(UsuarioDTO dto) {
		if (dto == null) return null;

		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setNome(dto.getNome());
		usuario.setNomeSocial(dto.getNomeSocial());
		usuario.setPerfis(dto.getPerfis());
		usuario.setTelefones(dto.getTelefones());
		usuario.setEndereco(dto.getEndereco());
		usuario.setDocumentos(dto.getDocumentos());
		usuario.setEmails(dto.getEmails());

		// A empresa não é setada aqui diretamente, pois precisa ser buscada via repositório
		// Isso será feito no controller ou no serviço.

		return usuario;
	}
}

