// MercadoriaRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMercadoria extends JpaRepository<Mercadoria, Long> {}
