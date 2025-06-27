package com.autobots.automanager.controles;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.repositorios.RepositorioUsuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PutMapping("/{usuarioId}/perfis")
    @Operation(summary = "Atualizar perfis do usuário (CLIENTE, FORNECEDOR, FUNCIONARIO)")
    public ResponseEntity<UsuarioDTO> atualizarPerfisUsuario(
            @PathVariable Long usuarioId,
            @RequestBody Set<PerfilUsuario> novosPerfis) {

        Usuario usuario = repositorioUsuario.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setPerfis(novosPerfis);
        repositorioUsuario.save(usuario);

        UsuarioDTO dto = usuarioMapper.toDTO(usuario);
        return ResponseEntity.ok(dto);
    }
}
