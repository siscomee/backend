package br.com.senac.siscomee.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senac.siscomee.model.entidades.Fornecedores;
import br.com.senac.siscomee.model.specifications.FornecedoresRet;
import br.com.senac.siscomee.model.specifications.RamoSetoresRet;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedores, Integer> {

	 Page<Fornecedores> findAll(Pageable pageable);
     
     List<Fornecedores> findByNmFornecedor(String nmFornecedor);

     
     Page<FornecedoresRet> findByInAtivo(short inAtivo, Pageable paging);

     
     Page<FornecedoresRet> findByInAtivoAndNmFornecedorContaining(short inAtivo, String nmFornecedor, Pageable paging);

     
     Page<FornecedoresRet> findByNmFornecedorContaining(String nmFornecedor, Pageable paging);
     
     @Query(nativeQuery = true,
             value = "SELECT f.id as Id, f.nm_fornecedor as NmFornecedor, f.nu_cnpj as NuCnpj, f.nu_telefone as NuTelefone, f.in_ativo as InAtivo,  f.usuario_id_atualiza as UsuarioIdAtualiza, f.dt_ult_atualiza as DtUltAtualiza, rs.id as RamoSetorId, rs.nm_ramo_setor as NmRamoSetor FROM fornecedores f" +
             		 " INNER JOIN ramos_setores as rs" +
             		 " ON f.ramo_setor_id = rs.id" ) 
 	Page<FornecedoresRet> listarFornecedores(Pageable paging);
     
    @Query(nativeQuery = true,
            value = "SELECT id as ID, nm_ramo_setor as nmRamoSetor FROM ramos_setores where in_ativo = '1' order by id asc")
	List<RamoSetoresRet> getRamoSetores();


}
