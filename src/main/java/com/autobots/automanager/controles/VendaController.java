package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.VendaDTO;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.mappers.VendaMapper;
import com.autobots.automanager.repositorios.RepositorioVenda;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Vendas", description = "Gerenciamento de vendas")
public class VendaController {

    @Autowired
    private RepositorioVenda repositorioVenda;

    @Autowired
    private VendaMapper vendaMapper;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioMercadoria repositorioMercadoria;

    @Autowired
    private RepositorioServico repositorioServico;

    @Autowired
    private RepositorioVeiculo repositorioVeiculo;

    // Create (Cadastro)
    @PostMapping
    @Operation(summary = "Cadastrar venda")
    public ResponseEntity<VendaDTO> cadastrarVenda(@RequestBody VendaDTO vendaDTO) {
        // Buscar o usuário cliente e o usuário funcionário pelos IDs fornecidos
        Usuario cliente = repositorioUsuario.findById(vendaDTO.getId()).orElse(null);
        Usuario funcionario = repositorioUsuario.findById(vendaDTO.getId()).orElse(null);
        
        // Verifica se os usuários existem
        if (cliente == null || funcionario == null) {
            return ResponseEntity.badRequest().build();
        }

        // Verifica se o cliente tem o perfil CLIENTE e o funcionário tem o perfil FUNCIONARIO
        boolean isCliente = cliente.getPerfis().contains(PerfilUsuario.CLIENTE);
        boolean isFuncionario = funcionario.getPerfis().contains(PerfilUsuario.FUNCIONARIO);
        
        if (!isCliente || !isFuncionario) {
            return ResponseEntity.badRequest().build();
        }

        // Verificar se o veículo existe
        if (repositorioVeiculo.findById(vendaDTO.getVeiculo().getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Criar e salvar a venda
        Venda venda = vendaMapper.toEntity(vendaDTO);
        venda = repositorioVenda.save(venda);
        VendaDTO dto = vendaMapper.toDTO(venda);
        return ResponseEntity.status(201).body(dto);
    }








    // Read (Buscar todos)
    @GetMapping
    @Operation(summary = "Listar todas as vendas")
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        List<Venda> vendas = repositorioVenda.findAll();
        List<VendaDTO> vendaDTOs = vendas.stream()
                                         .map(vendaMapper::toDTO)
                                         .collect(Collectors.toList());
        return ResponseEntity.ok(vendaDTOs);
    }

    // Read (Buscar por ID)
    @GetMapping("/{id}")
    @Operation(summary = "Buscar venda por ID")
    public ResponseEntity<VendaDTO> buscarVendaPorId(@PathVariable Long id) {
        Venda venda = repositorioVenda.findById(id).orElse(null);
        if (venda == null) {
            return ResponseEntity.notFound().build();
        }
        VendaDTO dto = vendaMapper.toDTO(venda);
        return ResponseEntity.ok(dto);
    }

    // Update (Atualizar)
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados da venda")
    public ResponseEntity<VendaDTO> atualizarVenda(@PathVariable Long id, @RequestBody VendaDTO vendaDTO) {
        Venda vendaExistente = repositorioVenda.findById(id).orElse(null);
        if (vendaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizando os dados
        vendaExistente.setIdentificacao(vendaDTO.getIdentificacao());
        vendaExistente.setCadastro(vendaDTO.getCadastro());
        vendaExistente.setMercadorias(
            vendaDTO.getMercadorias().stream().map(mercadoriaDTO -> repositorioMercadoria.findById(mercadoriaDTO.getId()).orElse(null)).collect(Collectors.toSet())
        );
        vendaExistente.setServicos(
            vendaDTO.getServicos().stream().map(servicoDTO -> repositorioServico.findById(servicoDTO.getId()).orElse(null)).collect(Collectors.toSet())
        );
        vendaExistente.setVeiculo(repositorioVeiculo.findById(vendaDTO.getVeiculo().getId()).orElse(null));

        Venda vendaAtualizada = repositorioVenda.save(vendaExistente);
        VendaDTO dto = vendaMapper.toDTO(vendaAtualizada);
        return ResponseEntity.ok(dto);
    }

    // Delete (Deletar)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar venda por ID")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        Venda venda = repositorioVenda.findById(id).orElse(null);
        if (venda == null) {
            return ResponseEntity.notFound().build();
        }
        repositorioVenda.delete(venda);
        return ResponseEntity.noContent().build();
    }
}
