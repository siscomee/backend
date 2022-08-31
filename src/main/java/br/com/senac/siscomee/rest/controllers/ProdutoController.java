package br.com.senac.siscomee.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import br.com.senac.siscomee.model.services.ProdutoService;
import br.com.senac.siscomee.model.entidades.Produtos;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoService produtoService;

	@ApiOperation(value="método p/ listar todos os produtos")
	@RequestMapping(path = "/listarTodos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarProdutos() {
		try {
			return ResponseEntity.ok(produtoService.listarProdutos());
		}catch (Exception e) {
			LOGGER.error("Erro ao listar todos produtos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
	@ApiOperation(value="método p/ adicionar os produtos")
    @PostMapping("/adicionar")
	public Produtos addProduto (@RequestBody Produtos produto)
    {
		return produtoService.addProduto(produto);
	}
		
	@ApiOperation(value="método p/ combo (tipo de produtos)")
	@RequestMapping(path = "/tipoDeProdutos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getTipoDeProdutos() {
		try {
			return ResponseEntity.ok(produtoService.getTipoDeProdutos());
		}catch (Exception e) {
			LOGGER.error("Erro ao listar tipo de produtos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="método p/ filtrar")
	@GetMapping(path = "/filtrar/{idTipoDeProduto}/{nmProduto}/{situacao}", produces = "application/json")
	public ResponseEntity<?> filtrar(@PathVariable int idTipoDeProduto, @PathVariable String nmProduto, @PathVariable int situacao) {	
		try {
			return ResponseEntity.ok(produtoService.filtrarProdutos(idTipoDeProduto, nmProduto, situacao));
		}catch (Exception e) {
			LOGGER.error("Erro ao filtrar!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="método p/ unicidade")
	@GetMapping(path = "/unicidade/{idTipoDeProduto}/{nmProduto}/{idProduto}", produces = "application/json")
	public ResponseEntity<?> unicidade(@PathVariable int idTipoDeProduto, @PathVariable String nmProduto, @PathVariable String idProduto) {	
		try {
			return ResponseEntity.ok(produtoService.unicidade(idTipoDeProduto, nmProduto, idProduto));
		}catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="método p/ editar os produtos")
	@PutMapping("/editar")
	public Produtos atualizarProduto(@RequestBody Produtos produto)
	{
		return produtoService.atualizarProduto(produto);
	}
	
	@ApiOperation(value="método p/ inativar")
	@GetMapping(path = "/inativar/{id}", produces = "application/json")
	public ResponseEntity<?> inativarProdutoByID(@PathVariable int id) {	
		try {
			return ResponseEntity.ok(produtoService.inativarProdutoByID(id));
		}catch (Exception e) {
			LOGGER.error("Erro ao inativar!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	}