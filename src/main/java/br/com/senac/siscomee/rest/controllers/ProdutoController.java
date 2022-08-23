package br.com.senac.siscomee.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.senac.siscomee.model.entidades.Produtos;
import br.com.senac.siscomee.model.services.ProdutoService;
import br.com.senac.siscomee.model.specifications.ProdutosRet;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@ApiOperation(value = "método p/ adicionar os produtos")	
	@PostMapping("/adicionar")
	public Produtos addProduto(@RequestBody Produtos ProdutosDTO) {
		return produtoService.saveProduto(ProdutosDTO);
	}

	@PostMapping("/addProduto")
	public List<Produtos> addFornecedor(@RequestBody List<Produtos> produto) {
		return produtoService.saveProdutoList(produto);
	}

	@GetMapping("/produto")
	public List<Produtos> findAllProduto() {
		return produtoService.getProduto();
	}

	@GetMapping("/buscar/{id}")
	public Produtos findProdutoById(@PathVariable int id) {
		return produtoService.getProdutoById(id);
	}

	@ApiOperation(value = "método p/ editar os produtos")	
	@PutMapping("/editar")
	public Produtos updateProduto(@RequestBody Produtos ProdutosDTO) {
		return produtoService.updateProduto(ProdutosDTO);
	}
	
	@ApiOperation(value = "método p/ inativar os produtos")	
	@PutMapping("/inativar")
	public Produtos inativarProduto(@RequestBody Produtos ProdutosDTO) {
		return produtoService.inativarProduto(ProdutosDTO);
	}

	@ApiOperation(value = "método p/ excluir os produtos")	
	@DeleteMapping("/excluir/{id}")
	public String deleteProduto(@PathVariable int id) {
		return produtoService.deleleProduto(id);
	}
	
	@ApiOperation(value = "método p/ listar todos os produtos")	
	@RequestMapping("/listarTodos")
	public ResponseEntity<?> listar(
			@RequestParam(required = false) String nmProduto, 
			@RequestParam(required = false) Float vlProduto, 
			@RequestParam(required = false) String tpMedida, 
			@RequestParam(required = false) String qtdProduto, 
			@RequestParam(required = false) Integer tipoProdutoId,  
			@RequestParam(required = false) Short inAtivo,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<ProdutosRet> pageProduto = produtoService.buscarProdutoPageSort(nmProduto, vlProduto, tpMedida, qtdProduto, tipoProdutoId, inAtivo, page, size);

			Map<String, Object> response = new HashMap<>();
			response.put("produtos", pageProduto.getContent());
			response.put("paginaAtual", pageProduto.getNumber());
			response.put("itensTotal", pageProduto.getTotalElements());
			response.put("paginasTotal", pageProduto.getTotalPages());
			response.put("paginaItens", pageProduto.getSize());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos os produtos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "método p/ verificar a unicidade")
	@RequestMapping("/unicidade")
	public ResponseEntity<?> listarCodigo(@RequestParam(required = true) String nmProduto) {
		try {
			return ResponseEntity.ok(produtoService.isSaveValid(nmProduto));
		} catch (Exception e) {
			LOGGER.error("Já existe!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "método p/ listar a combo de tipo de produtos")
	@RequestMapping(path = "/tipoProdutos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getRamoSetores() {
		try {
			return ResponseEntity.ok(produtoService.getTipoProdutos());
		}catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "método p/ listar todos os produtos")
	@RequestMapping(path = "/listar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarTodos() {
		try {
			return ResponseEntity.ok(produtoService.listarTodos());
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
