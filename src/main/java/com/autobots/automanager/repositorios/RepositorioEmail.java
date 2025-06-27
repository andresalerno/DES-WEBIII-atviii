// EmailRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEmail extends JpaRepository<Email, Long> {}
