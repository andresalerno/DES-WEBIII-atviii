package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entitades.*;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioCredencial;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credenciais")
@Tag(name = "Credenciais", description = "Gerenciamento de credenciais para usuários")
public class CredencialController {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioCredencial repositorioCredencial;

    // Create (Cadastrar credencial)
    @PostMapping("/usuario/{usuarioId}/senha")
    @Operation(summary = "Cadastrar credencial de senha para um usuário")
    public ResponseEntity<CredencialUsuarioSenha> cadastrarCredencialUsuarioSenha(@PathVariable Long usuarioId, @RequestBody CredencialUsuarioSenha credencialUsuarioSenha) {
        Usuario usuario = repositorioUsuario.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        credencialUsuarioSenha.setCriacao(new Date());
        usuario.getCredenciais().add(credencialUsuarioSenha);
        repositorioUsuario.save(usuario);  // Salva a credencial associada ao usuário
        return ResponseEntity.status(201).body(credencialUsuarioSenha);
    }

    // Create (Cadastrar credencial de código de barras para um usuário)
    @PostMapping("/usuario/{usuarioId}/codigo-barra")
    @Operation(summary = "Cadastrar credencial de código de barras para um usuário")
    public ResponseEntity<CredencialCodigoBarra> cadastrarCredencialCodigoBarra(@PathVariable Long usuarioId, @RequestBody CredencialCodigoBarra credencialCodigoBarra) {
        Usuario usuario = repositorioUsuario.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        credencialCodigoBarra.setCriacao(new Date());
        usuario.getCredenciais().add(credencialCodigoBarra);
        repositorioUsuario.save(usuario);  // Salva a credencial associada ao usuário
        return ResponseEntity.status(201).body(credencialCodigoBarra);
    }
}
