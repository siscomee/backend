package br.com.senac.siscomee.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.siscomee.model.services.TipoProdutoService;
import br.com.senac.siscomee.model.entidades.TiposProdutos;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tipoProduto")
public class TipoProdutoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TipoProdutoController.class);

	private final TipoProdutoService tipoProdutoService;

	public TipoProdutoController(TipoProdutoService tipoProdutoService) {
		this.tipoProdutoService = tipoProdutoService;
	}

	@ApiOperation(value = "método p/ adicionar os tipos de produtos")
	@PostMapping("/adicionar")
	public TiposProdutos addTipoProduto(@RequestBody TiposProdutos tipoProduto) {
		return tipoProdutoService.saveTipoProduto(tipoProduto);
	}

	@PostMapping("/addTipoProduto")
	public List<TiposProdutos> addTipoProduto(@RequestBody List<TiposProdutos> tipoProduto) {
		return tipoProdutoService.saveTipoProdutoList(tipoProduto);
	}

	@GetMapping("/tipoProduto")
	public List<TiposProdutos> findAllTipoProduto() {
		return tipoProdutoService.getTipoProduto();
	}

	@GetMapping("/buscar/{id}")
	public TiposProdutos findTiposProdutosById(@PathVariable int id) {
		return tipoProdutoService.getTipoProdutoById(id);
	}

	@ApiOperation(value = "método p/ editar os tipos de produtos")
	@PutMapping("/editar")
	public TiposProdutos updateTipoProduto(@RequestBody TiposProdutos tipoProduto) {
		return tipoProdutoService.updateTipoProduto(tipoProduto);
	}

	@ApiOperation(value = "método p/ inativar os tipos de produtos")
	@PutMapping("/inativar")
	public TiposProdutos inativarTipoProduto(@RequestBody TiposProdutos TipoProdutosDTO) {
		return tipoProdutoService.inativarTipoProduto(TipoProdutosDTO);
	}
	
	@ApiOperation(value = "método p/ excluir os tipos de produtos")
	@DeleteMapping("/excluir/{id}")
	public String deleteTipoProduto(@PathVariable int id) {
		return tipoProdutoService.deleleTipoProduto(id);
	}

	@ApiOperation(value = "método p/ listar os tipos de produtos")
	@RequestMapping("/listarTodos")
	public ResponseEntity<?> listar(
			@RequestParam(required = false) String dsTipoProduto, 
			@RequestParam(required = false) Short inAtivo,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<TiposProdutos> pageTipoProduto = tipoProdutoService.buscarTipoProdutoPageSort(dsTipoProduto, inAtivo, page, size);

			Map<String, Object> response = new HashMap<>();
			response.put("tipoProdutos", pageTipoProduto.getContent());
			response.put("paginaAtual", pageTipoProduto.getNumber());
			response.put("itensTotal", pageTipoProduto.getTotalElements());
			response.put("paginasTotal", pageTipoProduto.getTotalPages());
			response.put("paginaItens", pageTipoProduto.getSize());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "método p/ verificar a unicidade")
	@RequestMapping("/unicidade")
	public ResponseEntity<?> listarCodigo(@RequestParam(required = true) String dsTipoProduto) {
		try {
			return ResponseEntity.ok(tipoProdutoService.isSaveValid(dsTipoProduto));
		} catch (Exception e) {
			LOGGER.error("Já existe!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "método p/ listar todos os tipos de produtos")
	@RequestMapping(path = "/listar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarTodos() {
		try {
			return ResponseEntity.ok(tipoProdutoService.listarTodos());
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
