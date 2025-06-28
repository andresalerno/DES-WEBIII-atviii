package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Create (Cadastro de Usuário)
    @PostMapping
    @Operation(summary = "Cadastrar novo usuário")
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        // Verificar se o perfil é válido (pode ser CLIENTE, FUNCIONARIO ou FORNECEDOR)
        Set<PerfilUsuario> perfis = usuarioDTO.getPerfis();
        if (perfis == null || perfis.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Mapear o DTO para a entidade Usuario
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = repositorioUsuario.save(usuario);
        UsuarioDTO dto = usuarioMapper.toDTO(usuario);
        return ResponseEntity.status(201).body(dto);
    }

    // Read (Buscar todos os usuários)
    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<Usuario> usuarios = repositorioUsuario.findAll();
        List<UsuarioDTO> usuarioDTOs = usuarios.stream()
                                               .map(usuarioMapper::toDTO)
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(usuarioDTOs);
    }

    // Read (Buscar por ID)
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = repositorioUsuario.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO dto = usuarioMapper.toDTO(usuario);
        return ResponseEntity.ok(dto);
    }

    // Update (Atualizar dados do usuário)
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de um usuário")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = repositorioUsuario.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizando os dados do usuário
        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setNomeSocial(usuarioDTO.getNomeSocial());
        usuarioExistente.setPerfis(usuarioDTO.getPerfis());
        usuarioExistente.setTelefones(usuarioDTO.getTelefones());
        usuarioExistente.setEndereco(usuarioDTO.getEndereco());
        usuarioExistente.setDocumentos(usuarioDTO.getDocumentos());
        usuarioExistente.setEmails(usuarioDTO.getEmails());

        // Salvar a atualização
        Usuario usuarioAtualizado = repositorioUsuario.save(usuarioExistente);
        UsuarioDTO dto = usuarioMapper.toDTO(usuarioAtualizado);
        return ResponseEntity.ok(dto);
    }

    // Delete (Deletar usuário por ID)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário por ID")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        Usuario usuario = repositorioUsuario.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        repositorioUsuario.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
