package br.com.senac.siscomee.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.senac.siscomee.model.dto.TiposProdutosDTO;
import br.com.senac.siscomee.model.entidades.TiposProdutos;
import br.com.senac.siscomee.model.repository.TipoProdutoRepository;

@Service
public class TipoProdutoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TipoProdutoService.class);
	private Date dataLocal = new Date();

	private TipoProdutoRepository tipoProdutoRepository;

	public TipoProdutoService(TipoProdutoRepository tipoProdutoRepository) {
		this.tipoProdutoRepository = tipoProdutoRepository;
	}

	public List<TiposProdutos> listarTodos() {
		return this.tipoProdutoRepository.findAll();
	}

	public List<TiposProdutosDTO> listarTodosAsDTO() {

		List<TiposProdutosDTO> listaDTO = new ArrayList<>();
		for (TiposProdutos tipoProduto : this.tipoProdutoRepository.findAll()) {
			TiposProdutosDTO dto = new TiposProdutosDTO();
			dto.setId(tipoProduto.getId());
			dto.setDsTipoProduto(tipoProduto.getDsTipoProduto());
			dto.setInAtivo(tipoProduto.getInAtivo());
			dto.setUsuarioIdAtualiza(tipoProduto.getUsuarioIdAtualiza());
			dto.setDtUltAtualiza(tipoProduto.getDtUltAtualiza());
			listaDTO.add(dto);
		}

		return listaDTO;
	}
	
	public List<TiposProdutos> isSaveValid(String dsTipoProduto) {
		return tipoProdutoRepository.findByDsTipoProduto(dsTipoProduto);
	}

	public TiposProdutos saveTipoProduto(TiposProdutos tipoProduto) {
		return tipoProdutoRepository.save(tipoProduto);
	}

	public List<TiposProdutos> saveTipoProdutoList(List<TiposProdutos> tipoProduto) {
		return tipoProdutoRepository.saveAll(tipoProduto);
	}

	public List<TiposProdutos> getTipoProduto() {
		return tipoProdutoRepository.findAll();
	}

	public TiposProdutos getTipoProdutoById(int id) {
		return tipoProdutoRepository.findById(id).orElse(null);
	}

	public TiposProdutos updateTipoProduto(TiposProdutos tipoProduto) {
		TiposProdutos existeTipoProduto = tipoProdutoRepository.findById(tipoProduto.getId())
				.orElse(null);
		existeTipoProduto.setDsTipoProduto(tipoProduto.getDsTipoProduto());
		existeTipoProduto.setInAtivo(tipoProduto.getInAtivo());
		existeTipoProduto.setUsuarioIdAtualiza(tipoProduto.getUsuarioIdAtualiza());
		existeTipoProduto.setDtUltAtualiza(this.dataLocal);
		return tipoProdutoRepository.save(existeTipoProduto);
	}
	
	public TiposProdutos inativarTipoProduto(TiposProdutos tiposProdutosDTO) {
		TiposProdutos existeTipoProduto = tipoProdutoRepository.findById(tiposProdutosDTO.getId())
				.orElse(null);
		existeTipoProduto.setDsTipoProduto(tiposProdutosDTO.getDsTipoProduto());
		existeTipoProduto.setInAtivo(tiposProdutosDTO.getInAtivo());
		existeTipoProduto.setUsuarioIdAtualiza(tiposProdutosDTO.getUsuarioIdAtualiza());
		existeTipoProduto.setDtUltAtualiza(this.dataLocal);
		return tipoProdutoRepository.save(existeTipoProduto);
	}

	public String deleleTipoProduto(int id) {
		tipoProdutoRepository.deleteById(id);
		return "Cadastro removido com sucesso!" + id;
	}

	public Page<TiposProdutos> buscarTipoProdutoPageSort( String dsTipoProduto, Short inAtivo,
			Integer page, Integer size) {

		Pageable paging = PageRequest.of(page, size, Sort.by("inAtivo").descending());

		Page<TiposProdutos> pageTipoProduto;

		
		if ((inAtivo != null)  && (dsTipoProduto == null))
			pageTipoProduto = tipoProdutoRepository.findByInAtivo(inAtivo, paging);
		
		else if ((inAtivo != null) && (dsTipoProduto == null))
			pageTipoProduto = tipoProdutoRepository.findByInAtivoAndDsTipoProdutoContaining(inAtivo,dsTipoProduto,paging);
		
		else if ((inAtivo != null) && (dsTipoProduto != null))
			pageTipoProduto = tipoProdutoRepository.findByInAtivoAndDsTipoProdutoContaining(inAtivo, dsTipoProduto,
					paging);
		
		else if ((dsTipoProduto != null) && (inAtivo == null))
			pageTipoProduto = tipoProdutoRepository.findByDsTipoProdutoContaining(dsTipoProduto, paging);
		
		else if ((dsTipoProduto != null) && (inAtivo != null))
			pageTipoProduto = tipoProdutoRepository.findByInAtivoAndDsTipoProdutoContaining(inAtivo,
					dsTipoProduto, paging);
		else
			pageTipoProduto = tipoProdutoRepository.findAll(paging);

		return pageTipoProduto;
	}

}
