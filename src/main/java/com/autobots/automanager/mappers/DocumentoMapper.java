package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.DocumentoDTO;
import com.autobots.automanager.entitades.Documento;

public class DocumentoMapper {

	public DocumentoDTO toDTO(Documento doc) {
		if (doc == null) return null;

		DocumentoDTO dto = new DocumentoDTO();
		dto.setId(doc.getId());
		dto.setTipo(doc.getTipo());
		dto.setNumero(doc.getNumero());
		dto.setDataEmissao(doc.getDataEmissao());
		return dto;
	}

	public Documento toEntity(DocumentoDTO dto) {
		if (dto == null) return null;

		Documento doc = new Documento();
		doc.setId(dto.getId());
		doc.setTipo(dto.getTipo());
		doc.setNumero(dto.getNumero());
		doc.setDataEmissao(dto.getDataEmissao());
		return doc;
	}
}
