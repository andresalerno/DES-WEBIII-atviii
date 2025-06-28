package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.MercadoriaDTO;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.mappers.MercadoriaMapper;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mercadorias")
@Tag(name = "Mercadorias", description = "Gerenciamento de mercadorias")
public class MercadoriaController {

    @Autowired
    private RepositorioMercadoria repositorioMercadoria;

    @Autowired
    private MercadoriaMapper mercadoriaMapper;

    // Create (Cadastro)
    @PostMapping
    @Operation(summary = "Cadastrar mercadoria")
    public ResponseEntity<MercadoriaDTO> cadastrarMercadoria(@RequestBody MercadoriaDTO mercadoriaDTO) {
        Mercadoria mercadoria = mercadoriaMapper.toEntity(mercadoriaDTO);
        mercadoria = repositorioMercadoria.save(mercadoria);
        MercadoriaDTO dto = mercadoriaMapper.toDTO(mercadoria);
        return ResponseEntity.status(201).body(dto);
    }

    // Read (Buscar todos)
    @GetMapping
    @Operation(summary = "Listar todas as mercadorias")
    public ResponseEntity<List<MercadoriaDTO>> listarMercadorias() {
        List<Mercadoria> mercadorias = repositorioMercadoria.findAll();
        List<MercadoriaDTO> mercadoriaDTOs = mercadorias.stream()
                                                       .map(mercadoriaMapper::toDTO)
                                                       .collect(Collectors.toList());
        return ResponseEntity.ok(mercadoriaDTOs);
    }

    // Read (Buscar por ID)
    @GetMapping("/{id}")
    @Operation(summary = "Buscar mercadoria por ID")
    public ResponseEntity<MercadoriaDTO> buscarMercadoriaPorId(@PathVariable Long id) {
        Mercadoria mercadoria = repositorioMercadoria.findById(id).orElse(null);
        if (mercadoria == null) {
            return ResponseEntity.notFound().build();
        }
        MercadoriaDTO dto = mercadoriaMapper.toDTO(mercadoria);
        return ResponseEntity.ok(dto);
    }

    // Update (Atualizar)
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados da mercadoria")
    public ResponseEntity<MercadoriaDTO> atualizarMercadoria(@PathVariable Long id, @RequestBody MercadoriaDTO mercadoriaDTO) {
        Mercadoria mercadoriaExistente = repositorioMercadoria.findById(id).orElse(null);
        if (mercadoriaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizando os dados
        mercadoriaExistente.setNome(mercadoriaDTO.getNome());
        mercadoriaExistente.setQuantidade(mercadoriaDTO.getQuantidade());
        mercadoriaExistente.setValor(mercadoriaDTO.getValor());
        mercadoriaExistente.setCadastro(mercadoriaDTO.getCadastro());
        mercadoriaExistente.setValidade(mercadoriaDTO.getValidade());
        mercadoriaExistente.setFabricao(mercadoriaDTO.getFabricacao()); // Corrija se necessário
        mercadoriaExistente.setDescricao(mercadoriaDTO.getDescricao());

        Mercadoria mercadoriaAtualizada = repositorioMercadoria.save(mercadoriaExistente);
        MercadoriaDTO dto = mercadoriaMapper.toDTO(mercadoriaAtualizada);
        return ResponseEntity.ok(dto);
    }

    // Delete (Deletar)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar mercadoria por ID")
    public ResponseEntity<Void> deletarMercadoria(@PathVariable Long id) {
        Mercadoria mercadoria = repositorioMercadoria.findById(id).orElse(null);
        if (mercadoria == null) {
            return ResponseEntity.notFound().build();
        }
        repositorioMercadoria.delete(mercadoria);
        return ResponseEntity.noContent().build();
    }
}
