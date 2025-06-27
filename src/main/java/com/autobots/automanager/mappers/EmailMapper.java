package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.EmailDTO;
import com.autobots.automanager.entitades.Email;

public class EmailMapper {

	public EmailDTO toDTO(Email email) {
		if (email == null) return null;

		EmailDTO dto = new EmailDTO();
		dto.setId(email.getId());
		dto.setEndereco(email.getEndereco());
		return dto;
	}

	public Email toEntity(EmailDTO dto) {
		if (dto == null) return null;

		Email email = new Email();
		email.setId(dto.getId());
		email.setEndereco(dto.getEndereco());
		return email;
	}
}
