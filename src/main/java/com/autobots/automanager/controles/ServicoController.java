package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.ServicoDTO;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.mappers.ServicoMapper;
import com.autobots.automanager.repositorios.RepositorioServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Gerenciamento de serviços")
public class ServicoController {

    @Autowired
    private RepositorioServico repositorioServico;

    @Autowired
    private ServicoMapper servicoMapper;

    // Create (Cadastro)
    @PostMapping
    @Operation(summary = "Cadastrar serviço")
    public ResponseEntity<ServicoDTO> cadastrarServico(@RequestBody ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toEntity(servicoDTO);
        servico = repositorioServico.save(servico);
        ServicoDTO dto = servicoMapper.toDTO(servico);
        return ResponseEntity.status(201).body(dto);
    }

    // Read (Buscar todos)
    @GetMapping
    @Operation(summary = "Listar todos os serviços")
    public ResponseEntity<List<ServicoDTO>> listarServicos() {
        List<Servico> servicos = repositorioServico.findAll();
        List<ServicoDTO> servicoDTOs = servicos.stream()
                                              .map(servicoMapper::toDTO)
                                              .collect(Collectors.toList());
        return ResponseEntity.ok(servicoDTOs);
    }

    // Read (Buscar por ID)
    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço por ID")
    public ResponseEntity<ServicoDTO> buscarServicoPorId(@PathVariable Long id) {
        Servico servico = repositorioServico.findById(id).orElse(null);
        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        ServicoDTO dto = servicoMapper.toDTO(servico);
        return ResponseEntity.ok(dto);
    }

    // Update (Atualizar)
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do serviço")
    public ResponseEntity<ServicoDTO> atualizarServico(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        Servico servicoExistente = repositorioServico.findById(id).orElse(null);
        if (servicoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizando os dados
        servicoExistente.setNome(servicoDTO.getNome());
        servicoExistente.setValor(servicoDTO.getValor());
        servicoExistente.setDescricao(servicoDTO.getDescricao());

        Servico servicoAtualizado = repositorioServico.save(servicoExistente);
        ServicoDTO dto = servicoMapper.toDTO(servicoAtualizado);
        return ResponseEntity.ok(dto);
    }

    // Delete (Deletar)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar serviço por ID")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        Servico servico = repositorioServico.findById(id).orElse(null);
        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        repositorioServico.delete(servico);
        return ResponseEntity.noContent().build();
    }
}
