// EnderecoRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEndereco extends JpaRepository<Endereco, Long> {}
