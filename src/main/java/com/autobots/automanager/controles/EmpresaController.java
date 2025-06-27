package com.autobots.automanager.controles;

import java.util.List;
import java.util.stream.Collectors;

import com.autobots.automanager.dtos.EmpresaDTO;
import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.mappers.EmpresaMapper;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresas", description = "Operações relacionadas às empresas")
public class EmpresaController {

	@Autowired
	private RepositorioEmpresa repositorioEmpresa;

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	private final EmpresaMapper empresaMapper = new EmpresaMapper();
	private final UsuarioMapper usuarioMapper = new UsuarioMapper();

	@Operation(summary = "Listar todas as empresas")
	@GetMapping
	public CollectionModel<EntityModel<EmpresaDTO>> listarEmpresas() {
		List<EntityModel<EmpresaDTO>> empresas = repositorioEmpresa.findAll().stream()
			.map(empresaMapper::toDTO)
			.map(dto -> EntityModel.of(dto,
				Link.of("/empresas/" + dto.getId()).withSelfRel(),
				Link.of("/empresas/" + dto.getId() + "/usuarios").withRel("usuarios")
			))
			.collect(Collectors.toList());

		return CollectionModel.of(empresas, Link.of("/empresas").withSelfRel());
	}

	@Operation(summary = "Buscar empresa por ID")
	@ApiResponse(responseCode = "200", description = "Empresa encontrada")
	@ApiResponse(responseCode = "404", description = "Empresa não encontrada")
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<EmpresaDTO>> buscarEmpresa(@PathVariable Long id) {
		return repositorioEmpresa.findById(id)
			.map(empresaMapper::toDTO)
			.map(dto -> EntityModel.of(dto,
				Link.of("/empresas/" + dto.getId()).withSelfRel(),
				Link.of("/empresas/" + dto.getId() + "/usuarios").withRel("usuarios")
			))
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Cadastrar nova empresa")
	@ApiResponse(responseCode = "201", description = "Empresa criada com sucesso")
	@PostMapping
	public ResponseEntity<EntityModel<EmpresaDTO>> cadastrarEmpresa(@RequestBody EmpresaDTO dto) {
		Empresa empresa = empresaMapper.toEntity(dto);
		Empresa salva = repositorioEmpresa.save(empresa);
		EmpresaDTO resposta = empresaMapper.toDTO(salva);

		EntityModel<EmpresaDTO> model = EntityModel.of(resposta,
			Link.of("/empresas/" + resposta.getId()).withSelfRel(),
			Link.of("/empresas").withRel("todas"));

		return ResponseEntity.created(Link.of("/empresas/" + resposta.getId()).toUri()).body(model);
	}

	@Operation(summary = "Listar usuários vinculados a uma empresa")
	@ApiResponse(responseCode = "200", description = "Usuários listados com sucesso")
	@GetMapping("/{empresaId}/usuarios")
	public ResponseEntity<CollectionModel<EntityModel<UsuarioDTO>>> listarUsuariosPorEmpresa(@PathVariable Long empresaId) {
		if (!repositorioEmpresa.existsById(empresaId)) return ResponseEntity.notFound().build();

		List<EntityModel<UsuarioDTO>> usuarios = repositorioUsuario.findByEmpresaId(empresaId).stream()
			.map(usuarioMapper::toDTO)
			.map(dto -> EntityModel.of(dto,
				Link.of("/usuarios/" + dto.getId()).withSelfRel()
			))
			.collect(Collectors.toList());

		return ResponseEntity.ok(CollectionModel.of(usuarios, Link.of("/empresas/" + empresaId + "/usuarios").withSelfRel()));
	}

	@Operation(summary = "Associar um usuário a uma empresa")
	@ApiResponse(responseCode = "200", description = "Usuário associado com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuário ou empresa não encontrados")
	@PutMapping("/{empresaId}/usuarios/{usuarioId}")
	public ResponseEntity<Void> associarUsuarioAEmpresa(@PathVariable Long empresaId, @PathVariable Long usuarioId) {
		var empresa = repositorioEmpresa.findById(empresaId);
		var usuario = repositorioUsuario.findById(usuarioId);

		if (empresa.isEmpty() || usuario.isEmpty()) return ResponseEntity.notFound().build();

		Usuario user = usuario.get();
		user.setEmpresa(empresa.get());
		repositorioUsuario.save(user);

		return ResponseEntity.ok().build();
	}
}
