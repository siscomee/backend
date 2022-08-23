package br.com.senac.siscomee.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.senac.siscomee.model.entidades.Usuarios;

public interface UsuarioRepository extends CrudRepository<Usuarios, Integer> {
	
}