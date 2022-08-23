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

import br.com.senac.siscomee.model.services.RamoSetorService;
import br.com.senac.siscomee.model.entidades.RamosSetores;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ramoSetor")
public class RamoSetorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RamoSetorController.class);

	private final RamoSetorService ramoSetorService;

	public RamoSetorController(RamoSetorService ramoSetorService) {
		this.ramoSetorService = ramoSetorService;
	}

	@ApiOperation(value = "método p/ adicionar o ramo")
	@PostMapping("/adicionar")
	public RamosSetores addRamoSetor(@RequestBody RamosSetores ramoSetor) {
		return ramoSetorService.saveRamoSetor(ramoSetor);
	}

	@PostMapping("/addRamoSetor")
	public List<RamosSetores> addRamoSetor(@RequestBody List<RamosSetores> ramoSetor) {
		return ramoSetorService.saveRamoSetorList(ramoSetor);
	}

	@GetMapping("/ramoSetor")
	public List<RamosSetores> findAllRamoSetor() {
		return ramoSetorService.getRamoSetor();
	}

	@GetMapping("/buscar/{id}")
	public RamosSetores findRamoSetorById(@PathVariable int id) {
		return ramoSetorService.getRamoSetorById(id);
	}

	@ApiOperation(value = "método p/ editar o ramo")
	@PutMapping("/editar")
	public RamosSetores updateRamoSetor(@RequestBody RamosSetores ramoSetor) {
		return ramoSetorService.updateRamoSetor(ramoSetor);
	}
	

	@ApiOperation(value = "método p/ inativar os ramos")
	@PutMapping("/inativar")
	public RamosSetores inativarRamoSetor(@RequestBody RamosSetores RamosSetoresDTO) {
		return ramoSetorService.inativarRamoSetor(RamosSetoresDTO);
	}

	@ApiOperation(value = "método p/ excluir o ramo")
	@DeleteMapping("/excluir/{id}")
	public String deleteRamoSetor(@PathVariable int id) {
		return ramoSetorService.deleleRamoSetor(id);
	}

	@ApiOperation(value = "método p/ listar todos os ramos")
	@RequestMapping("/listarTodos")
	public ResponseEntity<?> listar(
			@RequestParam(required = false) String nmRamoSetor, 
			@RequestParam(required = false) Short inAtivo,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<RamosSetores> pageRamoSetor = ramoSetorService.buscarRamoSetorPageSort(nmRamoSetor, inAtivo, page, size);

			Map<String, Object> response = new HashMap<>();
			response.put("ramoSetores", pageRamoSetor.getContent());
			response.put("paginaAtual", pageRamoSetor.getNumber());
			response.put("itensTotal", pageRamoSetor.getTotalElements());
			response.put("paginasTotal", pageRamoSetor.getTotalPages());
			response.put("paginaItens", pageRamoSetor.getSize());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "método p/ verificar a unicidade")
	@RequestMapping("/unicidade")
	public ResponseEntity<?> listarCodigo(@RequestParam(required = true) String nmRamoSetor) {
		try {
			return ResponseEntity.ok(ramoSetorService.isSaveValid(nmRamoSetor));
		} catch (Exception e) {
			LOGGER.error("Já existe!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "método p/ listar todos os ramos")
	@RequestMapping(path = "/listar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listarTodos() {
		try {
			return ResponseEntity.ok(ramoSetorService.listarTodos());
		} catch (Exception e) {
			LOGGER.error("Erro ao listar todos!", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
