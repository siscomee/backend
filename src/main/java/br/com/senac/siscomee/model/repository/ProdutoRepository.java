package br.com.senac.siscomee.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senac.siscomee.model.entidades.Produtos;
import br.com.senac.siscomee.model.specifications.ProdutosRet;
import br.com.senac.siscomee.model.specifications.TipoProdutosRet;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {

	 Page<Produtos> findAll(Pageable pageable);
     
     List<Produtos> findByNmProduto(String nmProduto);

     
     Page<ProdutosRet> findByInAtivo(short inAtivo, Pageable paging);

     
     Page<ProdutosRet> findByInAtivoAndNmProdutoContaining(short inAtivo, String nmProduto, Pageable paging);

     
     Page<ProdutosRet> findByNmProdutoContaining(String nmProduto, Pageable paging);
     
     @Query(nativeQuery = true,
             value = "SELECT p.id as Id, p.nm_produto as NmProduto, p.vl_produto as VlProduto, p.tp_medida as TpMedida, p.qtd_produto as QtdProduto, p.in_ativo as InAtivo,p.usuario_id_atualiza as UsuarioIdAtualiza, p.dt_ult_atualiza as DtUltAtualiza, tp.id as TipoProdutoId, tp.ds_tipo_produto as DsTipoProduto FROM produtos p" +
             		 " INNER JOIN tipos_produtos as tp" +
             		 " ON p.tipo_produto_id = tp.id" ) 
 	Page<ProdutosRet> listarProdutos(Pageable paging);
     
    @Query(nativeQuery = true,
            value = "SELECT id as ID, ds_tipo_produto as dsTipoProduto FROM tipos_produtos where in_ativo = '1' order by id asc")
	List<TipoProdutosRet> getTipoProdutos();


}
