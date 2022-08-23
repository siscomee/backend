package br.com.senac.siscomee.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.senac.siscomee.model.entidades.Perfis;

public interface PerfisRepository extends CrudRepository<Perfis, Integer> {
	
}