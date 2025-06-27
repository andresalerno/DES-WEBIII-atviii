
//UsuarioRepositorio.java
package com.autobots.automanager.repositorios;

import com.autobots.automanager.entitades.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	List<Usuario> findByEmpresaId(Long empresaId);
}

