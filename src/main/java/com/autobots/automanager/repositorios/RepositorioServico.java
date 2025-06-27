// ServicoRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioServico extends JpaRepository<Servico, Long> {}
