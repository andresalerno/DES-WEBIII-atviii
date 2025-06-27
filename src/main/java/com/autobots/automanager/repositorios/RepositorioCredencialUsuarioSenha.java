// CredencialUsuarioSenhaRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.CredencialUsuarioSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCredencialUsuarioSenha extends JpaRepository<CredencialUsuarioSenha, Long> {}
