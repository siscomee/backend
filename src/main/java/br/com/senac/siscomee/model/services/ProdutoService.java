package br.com.senac.siscomee.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.senac.siscomee.model.dto.ProdutosDTO;
import br.com.senac.siscomee.model.entidades.Fornecedores;
import br.com.senac.siscomee.model.entidades.Produtos;
import br.com.senac.siscomee.model.repository.ProdutoRepository;
import br.com.senac.siscomee.model.specifications.ProdutosRet;
import br.com.senac.siscomee.model.specifications.TipoProdutosRet;


@Service
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);
    private Date dataLocal = new Date();

    @Autowired
	private ProdutoRepository produtoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProdutosRet> listarProdutos() {
    	return this.produtoRepository.listarProdutos();
	}

	public List<ProdutosDTO> listarProdutosDTO()
	{
		List<ProdutosDTO> listaDTO = new ArrayList<>();
		
		for(Produtos Produto : this.produtoRepository.findAll()) {
			ProdutosDTO dto = new ProdutosDTO();
				 dto.setId(Produto.getId());
				 dto.setNmProduto(Produto.getNmProduto());
				 dto.setVlProduto(Produto.getVlProduto());
				 dto.setTpMedida(Produto.getTpMedida());
	             dto.setTipoProdutoId(Produto.getTipoProdutoId().getId());
	             dto.setDsTipoProduto(Produto.getTipoProdutoId().getDsTipoProduto());
				 dto.setInAtivo(Produto.getInAtivo());
				 dto.setDtUltAtualiza(Produto.getDtUltAtualiza());
				 dto.setUsuarioIdAtualiza(Produto.getUsuarioIdAtualiza());
				 listaDTO.add(dto);
			}
			
			return listaDTO;
	}
	
	public Produtos addProduto(Produtos produto)
	{
		return produtoRepository.save(produto);
	}
	
	public Produtos atualizarProduto(Produtos produto)
	{
		Produtos Produto = produtoRepository.findById(produto.getId()).orElse(null);

		Produto.setNmProduto(produto.getNmProduto());
		Produto.setVlProduto(produto.getVlProduto());
		Produto.setTpMedida(produto.getTpMedida());
		Produto.setQtdProduto(produto.getQtdProduto());
		Produto.setInAtivo(produto.getInAtivo());
		Produto.setTipoProdutoId(produto.getTipoProdutoId());
		Produto.setUsuarioIdAtualiza(produto.getUsuarioIdAtualiza());
		Produto.setDtUltAtualiza(this.dataLocal);
		
		return produtoRepository.save(Produto);
	}
	
	// combo tipos de produtos
	public List<TipoProdutosRet> getTipoDeProdutos()
	{
		return produtoRepository.getTipoDeProdutos();
	}
	
	public Produtos getProdutoById(int id)
	{
		return produtoRepository.findById(id).orElse(null);
	}	
	
	public Produtos inativarProdutoByID(int idProduto)
	{
		Produtos Produto = produtoRepository.findById(idProduto).orElse(null);

		Produto.setInAtivo((short) 0);		
		Produto.setDtUltAtualiza(this.dataLocal);

		return produtoRepository.save(Produto);
	}
	
	public List<ProdutosDTO> filtrarProdutos(int idTipoDeProduto, String nmProduto, int situacao)
	{
		String Sql = "select p from Produtos p "
				   
				   	+ "where 1=1";

					if(idTipoDeProduto != 0)
					{
						Sql += " and p.tipoProdutoId = " + idTipoDeProduto;
					}
					
					if(!nmProduto.equals(null) && !nmProduto.equals("nulo"))
					{
						Sql += " and p.nmProduto like '%" + nmProduto + "%'";
					}
					
					if(situacao != -1)
					{
						Sql += " and p.inAtivo = " + situacao;
					}
				
					Sql += " order by p.inAtivo desc";

					Query q = entityManager.createQuery(Sql);
		
					List<Produtos> Lista = q.getResultList();
					
					List<ProdutosDTO> listaDTO = new ArrayList<>();
					
					for(Produtos Produto : Lista)
					{
						ProdutosDTO dto = new ProdutosDTO();
							 
							 dto.setId(Produto.getId());
							 dto.setNmProduto(Produto.getNmProduto());
							 dto.setVlProduto(Produto.getVlProduto());
							 dto.setTpMedida(Produto.getTpMedida());
							 dto.setQtdProduto(Produto.getQtdProduto());
				             dto.setTipoProdutoId(Produto.getTipoProdutoId().getId());
							 dto.setInAtivo(Produto.getInAtivo());
							 dto.setDsTipoProduto(Produto.getTipoProdutoId().getDsTipoProduto());
							 dto.setDtUltAtualiza(Produto.getDtUltAtualiza());
							 dto.setUsuarioIdAtualiza(Produto.getUsuarioIdAtualiza());
							 listaDTO.add(dto);
					}
						
					return listaDTO;
	}
	
	public Boolean unicidade(int idTipoDeProduto, String nmProduto, String id)
	{
		String Sql = "select p from Produtos p "
				   	+ "where 1=1 "
					+ "and p.tipoProdutoId = " + idTipoDeProduto;
					
					if(!nmProduto.equals(null) && !nmProduto.equals("nulo"))
					{
						Sql += " and p.nmProduto = '" + nmProduto + "'";
					}

					if(!id.equals("null"))
					{
						Sql += " and p.id != " + id;
					}

					Query q = entityManager.createQuery(Sql);
		
					List<Fornecedores> Lista = q.getResultList();
						
					return Lista.size() > 0 ? false : true;
	}

	@Transactional
	public void execute(String sql)
	{
		entityManager.createNativeQuery(sql).getResultList();		
	}
}