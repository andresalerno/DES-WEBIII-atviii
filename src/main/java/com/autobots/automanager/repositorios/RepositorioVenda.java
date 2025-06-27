// VendaRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVenda extends JpaRepository<Venda, Long> {}
