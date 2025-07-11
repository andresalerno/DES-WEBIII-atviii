// DocumentoRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDocumento extends JpaRepository<Documento, Long> {}
