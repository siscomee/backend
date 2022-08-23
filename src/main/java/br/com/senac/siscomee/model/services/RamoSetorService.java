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

import br.com.senac.siscomee.model.dto.RamosSetoresDTO;
import br.com.senac.siscomee.model.entidades.RamosSetores;
import br.com.senac.siscomee.model.repository.RamoSetorRepository;


@Service
public class RamoSetorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RamoSetorService.class);
	private Date dataLocal = new Date();

	private RamoSetorRepository ramoSetorRepository;

	public RamoSetorService(RamoSetorRepository ramoSetorRepository) {
		this.ramoSetorRepository = ramoSetorRepository;
	}

	public List<RamosSetores> listarTodos() {
		return this.ramoSetorRepository.findAll();
	}

	public List<RamosSetoresDTO> listarTodosAsDTO() {

		List<RamosSetoresDTO> listaDTO = new ArrayList<>();
		for (RamosSetores ramoSetor : this.ramoSetorRepository.findAll()) {
			RamosSetoresDTO dto = new RamosSetoresDTO();
			dto.setId(ramoSetor.getId());
			dto.setNmRamoSetor(ramoSetor.getNmRamoSetor());
			dto.setInAtivo(ramoSetor.getInAtivo());
			dto.setUsuarioIdAtualiza(ramoSetor.getUsuarioIdAtualiza());
			dto.setDtUltAtualiza(ramoSetor.getDtUltAtualiza());
			listaDTO.add(dto);
		}

		return listaDTO;
	}
	
	public List<RamosSetores> isSaveValid(String nmRamoSetor) {
		return ramoSetorRepository.findByNmRamoSetor(nmRamoSetor);
	}

	public RamosSetores saveRamoSetor(RamosSetores ramoSetor) {
		return ramoSetorRepository.save(ramoSetor);
	}

	public List<RamosSetores> saveRamoSetorList(List<RamosSetores> ramoSetor) {
		return ramoSetorRepository.saveAll(ramoSetor);
	}

	public List<RamosSetores> getRamoSetor() {
		return ramoSetorRepository.findAll();
	}

	public RamosSetores getRamoSetorById(int id) {
		return ramoSetorRepository.findById(id).orElse(null);
	}

	public RamosSetores updateRamoSetor(RamosSetores ramoSetor) {
		RamosSetores existeRamoSetor = ramoSetorRepository.findById(ramoSetor.getId())
				.orElse(null);
		existeRamoSetor.setNmRamoSetor(ramoSetor.getNmRamoSetor());
		existeRamoSetor.setInAtivo(ramoSetor.getInAtivo());
		existeRamoSetor.setUsuarioIdAtualiza(ramoSetor.getUsuarioIdAtualiza());
		existeRamoSetor.setDtUltAtualiza(this.dataLocal);
		return ramoSetorRepository.save(existeRamoSetor);
	}
	
	public RamosSetores inativarRamoSetor(RamosSetores ramosSetoresDTO) {
		RamosSetores existeRamoSetor = ramoSetorRepository.findById(ramosSetoresDTO.getId())
				.orElse(null);
		existeRamoSetor.setNmRamoSetor(ramosSetoresDTO.getNmRamoSetor());
		existeRamoSetor.setInAtivo(ramosSetoresDTO.getInAtivo());
		existeRamoSetor.setUsuarioIdAtualiza(ramosSetoresDTO.getUsuarioIdAtualiza());
		existeRamoSetor.setDtUltAtualiza(this.dataLocal);
		return ramoSetorRepository.save(ramosSetoresDTO);
	}

	public String deleleRamoSetor(int id) {
		ramoSetorRepository.deleteById(id);
		return "Cadastro removido com sucesso!" + id;
	}

	public Page<RamosSetores> buscarRamoSetorPageSort( String nmRamoSetor, Short inAtivo,
			Integer page, Integer size) {

		Pageable paging = PageRequest.of(page, size, Sort.by("inAtivo").descending());

		Page<RamosSetores> pageRamoSetor;

		
		if ((inAtivo != null)  && (nmRamoSetor == null))
			pageRamoSetor = ramoSetorRepository.findByInAtivo(inAtivo, paging);
		
		else if ((inAtivo != null) && (nmRamoSetor == null))
			pageRamoSetor = ramoSetorRepository.findByInAtivoAndNmRamoSetorContaining(inAtivo,nmRamoSetor,paging);
		
		else if ((inAtivo != null) && (nmRamoSetor != null))
			pageRamoSetor = ramoSetorRepository.findByInAtivoAndNmRamoSetorContaining(inAtivo, nmRamoSetor,
					paging);
		
		else if ((nmRamoSetor != null) && (inAtivo == null))
			pageRamoSetor = ramoSetorRepository.findByNmRamoSetorContaining(nmRamoSetor, paging);
		
		else if ((nmRamoSetor != null) && (inAtivo != null))
			pageRamoSetor = ramoSetorRepository.findByInAtivoAndNmRamoSetorContaining(inAtivo,
					 nmRamoSetor, paging);
		else
			pageRamoSetor = ramoSetorRepository.findAll(paging);

		return pageRamoSetor;
	}
	

}
