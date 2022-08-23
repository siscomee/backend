package br.com.senac.siscomee.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.siscomee.model.entidades.RamosSetores;


@Repository
public interface RamoSetorRepository extends JpaRepository<RamosSetores, Integer> {

        Page<RamosSetores> findAll(Pageable pageable);
        
        List<RamosSetores> findByNmRamoSetor(String nmRamoSetor);

        
        Page<RamosSetores> findByInAtivo(short inAtivo, Pageable paging);

        
        Page<RamosSetores> findByInAtivoAndNmRamoSetorContaining(short inAtivo, String nmRamoSetor, Pageable paging);

        
        Page<RamosSetores> findByNmRamoSetorContaining(String nmRamoSetor, Pageable paging);

}
