package br.com.senac.siscomee.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.senac.siscomee.model.dto.ProdutosDTO;
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

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produtos> listarTodos() {
		return this.produtoRepository.findAll();
	}

	public List<ProdutosDTO> listarTodosAsDTO() {

		List<ProdutosDTO> listaDTO = new ArrayList<>();
		for (Produtos produto : this.produtoRepository.findAll()) {
			ProdutosDTO dto = new ProdutosDTO();
			dto.setId(produto.getId());
			dto.setNmProduto(produto.getNmProduto());
			dto.setVlProduto(produto.getVlProduto());
			dto.setTpMedida(produto.getTpMedida());
			dto.setQtdProduto(produto.getQtdProduto());
			dto.setTipoProdutoId(produto.getTipoProdutoId());
			dto.setInAtivo(produto.getInAtivo());
			dto.setUsuarioIdAtualiza(produto.getUsuarioIdAtualiza());
			dto.setDtUltAtualiza(produto.getDtUltAtualiza());
			listaDTO.add(dto);
		}

		return listaDTO;
	}
	
	public List<Produtos> isSaveValid(String nmProduto) {
		return produtoRepository.findByNmProduto(nmProduto);
	}

	public Produtos saveProduto(Produtos produto) {
		return produtoRepository.save(produto);
	}

	public List<Produtos> saveProdutoList(List<Produtos> produto) {
		return produtoRepository.saveAll(produto);
	}

	public List<Produtos> getProduto() {
		return produtoRepository.findAll();
	}

	public Produtos getProdutoById(int id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public Produtos updateProduto(Produtos produtosDTO) {
		Produtos existeProduto = produtoRepository.findById(produtosDTO.getId())
				.orElse(null);
		existeProduto.setNmProduto(produtosDTO.getNmProduto());
		existeProduto.setVlProduto(produtosDTO.getVlProduto());
		existeProduto.setTpMedida(produtosDTO.getTpMedida());
		existeProduto.setQtdProduto(produtosDTO.getQtdProduto());
		existeProduto.setTipoProdutoId(produtosDTO.getTipoProdutoId());
		existeProduto.setInAtivo(produtosDTO.getInAtivo());
		existeProduto.setUsuarioIdAtualiza(produtosDTO.getUsuarioIdAtualiza());
		existeProduto.setDtUltAtualiza(this.dataLocal);
		return produtoRepository.save(existeProduto);
	}
	
	public Produtos inativarProduto(Produtos produtosDTO) {
		Produtos existeProduto = produtoRepository.findById(produtosDTO.getId())
				.orElse(null);
		existeProduto.setNmProduto(produtosDTO.getNmProduto());
		existeProduto.setVlProduto(produtosDTO.getVlProduto());
		existeProduto.setTpMedida(produtosDTO.getTpMedida());
		existeProduto.setQtdProduto(produtosDTO.getQtdProduto());
		existeProduto.setInAtivo(produtosDTO.getInAtivo());
		existeProduto.setUsuarioIdAtualiza(produtosDTO.getUsuarioIdAtualiza());
		existeProduto.setDtUltAtualiza(this.dataLocal);
		return produtoRepository.save(existeProduto);
	}

	public String deleleProduto(int id) {
		produtoRepository.deleteById(id);
		return "Cadastro removido com sucesso!" + id;
	}

	public Page<ProdutosRet> buscarProdutoPageSort( String nmProduto, Float vlProduto, String tpMedida, String qtdProduto, Integer tipoProdutoId,  Short inAtivo,
			Integer page, Integer size) {

		//Pageable paging = PageRequest.of(page, size, Sort.by("inAtivo").descending());
		

		Page<ProdutosRet> pageProduto;

		if ((inAtivo != null)  && (nmProduto == null))
			pageProduto = produtoRepository.findByInAtivo(inAtivo, null);
		
		else if ((inAtivo != null) && (nmProduto == null))
			pageProduto = produtoRepository.findByInAtivoAndNmProdutoContaining(inAtivo,nmProduto, null);
		

		else if ((inAtivo != null) && (nmProduto != null))
			pageProduto = produtoRepository.findByInAtivoAndNmProdutoContaining(inAtivo, nmProduto, null);
		
		else if ((nmProduto != null) && (inAtivo == null))
			pageProduto = produtoRepository.findByNmProdutoContaining(nmProduto, null);
		
		else if ((nmProduto != null) && (inAtivo != null))
			pageProduto = produtoRepository.findByInAtivoAndNmProdutoContaining(inAtivo,
					nmProduto, null);
		else
			pageProduto = produtoRepository.listarProdutos(null);

		return pageProduto;
	}
	
  // combo tipos produtos
	public List<TipoProdutosRet> getTipoProdutos()
	{
		return produtoRepository.getTipoProdutos();
	}

    public Page<ProdutosRet> listarProdutos() {
    	return this.produtoRepository.listarProdutos(null);
	}
	    
	    
}
