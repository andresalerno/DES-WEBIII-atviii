// TelefoneRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTelefone extends JpaRepository<Telefone, Long> {}
