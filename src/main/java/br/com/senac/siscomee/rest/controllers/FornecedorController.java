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

import br.com.senac.siscomee.model.services.FornecedorService;
import br.com.senac.siscomee.model.specifications.FornecedoresRet;
import br.com.senac.siscomee.model.entidades.Fornecedores;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorController.class);

	@Autowired
	private final FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@ApiOperation(value = "método p/ adicionar os fornecedores")
	@PostMapping("/adicionar")
	public Fornecedores addFornecedor(@RequestBody Fornecedores FornecedoresDTO) {
		return fornecedorService.saveFornecedor(FornecedoresDTO);
	}

	@PostMapping("/addFornecedor")
	public List<Fornecedores> addFornecedor(@RequestBody List<Fornecedores> fornecedor) {
		return fornecedorService.saveFornecedorList(fornecedor);
	}

	@GetMapping("/fornecedor")
	public List<Fornecedores> findAllFornecedor() {
		return fornecedorService.getFornecedor();
	}

	@GetMapping("/buscar/{id}")
	public Fornecedores findFornecedorById(@PathVariable int id) {
		return fornecedorService.getFornecedorById(id);
	}

	@ApiOperation(value = "método p/ editar os fornecedores")
	@PutMapping("/editar")
	public Fornecedores updateFornecedor(@RequestBody Fornecedores FornecedoresDTO) {
		return fornecedorService.updateFornecedor(FornecedoresDTO);
	}
	
	@ApiOperation(value = "método p/ inativar os fornecedores")
	@PutMapping("/inativar")
	public Fornecedores inativarFornecedor(@RequestBody Fornecedores FornecedoresDTO) {
		return fornecedorService.inativarFornecedor(FornecedoresDTO);
	}

	@ApiOperation(value = "método p/ excluir os fornecedores")
	@DeleteMapping("/excluir/{id}")
	public String deleteFornecedor(@PathVariable int id) {
		return fornecedorService.deleleFornecedor(id);
	}
	
	@ApiOperation(value = "método p/ listar todos os fornecedores")
	@RequestMapping("/listarTodos")
	public ResponseEntity<?> listar(
			@RequestParam(required = false) String nmFornecedor, 
			@RequestParam(required = false) String nuCnpj, 
			@RequestParam(required = false) String nuTelefone, 
			@RequestParam(required = false) Integer ramoSetorId, 
			@RequestParam(required = false) String nmRamoSetor, 
			@RequestParam(required = false) Short inAtivo,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<FornecedoresRet> pageFornecedor = fornecedorService.buscarFornecedorPageSort(nmFornecedor, nuCnpj, nuTelefone, ramoSetorId,nmRamoSetor, inAtivo, page, size);

			Map<String, Object> response = new HashMap<>();
			response.put("fornecedores", pageFornecedor.getContent());
			response.put("ramoSetores.nmRamoSetor", pageFornecedor.getContent());
			response.put("paginaAtual", pageFornecedor.getNumber());
			response.put("itensTotal", pageFornecedor.getTotalElements());
			response.put("paginasTotal", pageFornecedor.getTotalPages());
			response.put("paginaItens", pageFornecedor.getSize());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos os fornecedores!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "método p/ verificar a unicidade")
	@RequestMapping("/unicidade")
	public ResponseEntity<?> listarCodigo(@RequestParam(required = true) String nmFornecedor) {
		try {
			return ResponseEntity.ok(fornecedorService.isSaveValid(nmFornecedor));
		} catch (Exception e) {
			LOGGER.error("Já existe!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "método p/ listar a combo de ramos")
	@RequestMapping(path = "/ramoSetores", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getRamoSetores() {
		try {
			return ResponseEntity.ok(fornecedorService.getRamoSetores());
		}catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "método p/ listar todos os fornecedores")
	@RequestMapping(path = "/listar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarTodos() {
		try {
			return ResponseEntity.ok(fornecedorService.listarTodos());
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
