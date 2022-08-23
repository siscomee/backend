package br.com.senac.siscomee.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.senac.siscomee.model.entidades.Menus;

public interface MenusRepository extends CrudRepository<Menus, Integer> {
	
}