package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobots.automanager.entitades.Empresa;

@Repository
public interface RepositorioEmpresa extends JpaRepository<Empresa, Long> {
	//public Empresa findByRazaoSocial(String nome);
}