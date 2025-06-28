package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.VeiculoDTO;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.mappers.VeiculoMapper;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos", description = "Gerenciamento de veículos")
public class VeiculoController {

    @Autowired
    private RepositorioVeiculo repositorioVeiculo;

    @Autowired
    private VeiculoMapper veiculoMapper;

    // Create (Cadastro)
    @PostMapping
    @Operation(summary = "Cadastrar veículo")
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
        veiculo = repositorioVeiculo.save(veiculo);
        VeiculoDTO dto = veiculoMapper.toDTO(veiculo);
        return ResponseEntity.status(201).body(dto);
    }

    // Read (Buscar todos)
    @GetMapping
    @Operation(summary = "Listar todos os veículos")
    public ResponseEntity<List<VeiculoDTO>> listarVeiculos() {
        List<Veiculo> veiculos = repositorioVeiculo.findAll();
        List<VeiculoDTO> veiculoDTOs = veiculos.stream()
                                               .map(veiculoMapper::toDTO)
                                               .collect(Collectors.toList());
        return ResponseEntity.ok(veiculoDTOs);
    }

    // Read (Buscar por ID)
    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo por ID")
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorId(@PathVariable Long id) {
        Veiculo veiculo = repositorioVeiculo.findById(id).orElse(null);
        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }
        VeiculoDTO dto = veiculoMapper.toDTO(veiculo);
        return ResponseEntity.ok(dto);
    }

    // Update (Atualizar)
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do veículo")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculoExistente = repositorioVeiculo.findById(id).orElse(null);
        if (veiculoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizando os dados
        veiculoExistente.setModelo(veiculoDTO.getModelo());
        veiculoExistente.setPlaca(veiculoDTO.getPlaca());
        veiculoExistente.setTipo(veiculoDTO.getTipo());

        Veiculo veiculoAtualizado = repositorioVeiculo.save(veiculoExistente);
        VeiculoDTO dto = veiculoMapper.toDTO(veiculoAtualizado);
        return ResponseEntity.ok(dto);
    }

    // Delete (Deletar)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar veículo por ID")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        Veiculo veiculo = repositorioVeiculo.findById(id).orElse(null);
        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }
        repositorioVeiculo.delete(veiculo);
        return ResponseEntity.noContent().build();
    }
}
