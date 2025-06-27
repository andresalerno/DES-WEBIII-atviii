// CredencialRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCredencial extends JpaRepository<Credencial, Long> {}
