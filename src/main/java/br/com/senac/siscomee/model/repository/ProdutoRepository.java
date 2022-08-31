package br.com.senac.siscomee.model.repository;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senac.siscomee.model.entidades.Produtos;
import br.com.senac.siscomee.model.specifications.ProdutosRet;
import br.com.senac.siscomee.model.specifications.TipoProdutosRet;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {
	
	@Query(nativeQuery = true,
            value = "select p.id as Id, tp.ds_tipo_produto as DsTipoProduto, p.nm_produto as NmProduto, p.vl_produto as VlProduto, p.tp_medida as TpMedida, p.qtd_produto as QtdProduto, p.tipo_produto_id as TipoProdutoId, " +
            		"p.in_ativo as InAtivo, " +
            		"p.usuario_id_atualiza as UsuarioIdAtualiza, p.dt_ult_atualiza as DtUltAtualiza " +
            		"From produtos p " +
            		"INNER JOIN tipos_produtos as tp " +
            		"ON (p.tipo_produto_id = tp.id) " +
                    "order by p.in_ativo desc")
	List<ProdutosRet> listarProdutos();
	
	@Query(nativeQuery = true,
			 value = "select p.id as Id, tp.ds_tipo_produto as DsTipoProduto, p.nm_produto as NmProduto, p.vl_produto as VlProduto, p.tp_medida as TpMedida, p.qtd_produto as QtdProduto, p.tipo_produto_id as TipoProdutoId, " +
	            		"p.in_ativo as InAtivo, " +
	            		"p.usuario_id_atualiza as UsuarioIdAtualiza, p.dt_ult_atualiza as DtUltAtualiza " +
	            		"From produtos p " +
	            		"INNER JOIN tipos_produtos as tp " +
	            		"ON (p.tipo_produto_id = tp.id) " +
	                    "order by p.in_ativo desc")
	List<ProdutosRet> filtrarProdutos(@Param("idTipoDeProduto") Integer idTipoDeProduto, @Param("nmProduto") String nmProduto, @Param("situacao") Integer situacao);
	
	@Query(nativeQuery = true,
            value = "select tp.id AS Id, tp.ds_tipo_produto AS DsTipoProduto " +
            		"from tipos_produtos tp where in_ativo = '1' " +
                    "order by tp.in_ativo desc")
	List<TipoProdutosRet> getTipoDeProdutos();
	
	@Query(nativeQuery = true,
            value = "select P.* from produtos P inner join tipos_produtos RS ON (P.tipo_produto_id = TP.id) " +
                    "where TP.id = :tipo and P.nmProduto = :nmProduto and F.in_ativo = :situacao")
	List<Produtos> filtrarProduto(@Param("tipo") Long tipo,@Param("nmProduto") String nmProduto, @Param("situacao") Short situacao);

	List<Produtos> findAll(Direction desc, String string);

}
