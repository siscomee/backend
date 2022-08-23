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

import br.com.senac.siscomee.model.dto.FornecedoresDTO;
import br.com.senac.siscomee.model.entidades.Fornecedores;
import br.com.senac.siscomee.model.repository.FornecedorRepository;
import br.com.senac.siscomee.model.specifications.FornecedoresRet;
import br.com.senac.siscomee.model.specifications.RamoSetoresRet;


@Service
public class FornecedorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorService.class);
	private Date dataLocal = new Date();

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	public FornecedorService(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}

	public List<Fornecedores> listarTodos() {
		return this.fornecedorRepository.findAll();
	}

	public List<FornecedoresDTO> listarTodosAsDTO() {

		List<FornecedoresDTO> listaDTO = new ArrayList<>();
		for (Fornecedores fornecedor : this.fornecedorRepository.findAll()) {
			FornecedoresDTO dto = new FornecedoresDTO();
			dto.setId(fornecedor.getId());
			dto.setNmFornecedor(fornecedor.getNmFornecedor());
			dto.setNuCnpj(fornecedor.getNuCnpj());
			dto.setNuTelefone(fornecedor.getNuTelefone());
			dto.setRamoSetorId(fornecedor.getRamoSetorId());
			dto.setInAtivo(fornecedor.getInAtivo());
			dto.setUsuarioIdAtualiza(fornecedor.getUsuarioIdAtualiza());
			dto.setDtUltAtualiza(fornecedor.getDtUltAtualiza());
			listaDTO.add(dto);
		}

		return listaDTO;
	}
	
	public List<Fornecedores> isSaveValid(String nmFornecedor) {
		return fornecedorRepository.findByNmFornecedor(nmFornecedor);
	}

	public Fornecedores saveFornecedor(Fornecedores fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public List<Fornecedores> saveFornecedorList(List<Fornecedores> fornecedor) {
		return fornecedorRepository.saveAll(fornecedor);
	}

	public List<Fornecedores> getFornecedor() {
		return fornecedorRepository.findAll();
	}

	public Fornecedores getFornecedorById(int id) {
		return fornecedorRepository.findById(id).orElse(null);
	}

	public Fornecedores updateFornecedor(Fornecedores fornecedoresDTO) {
		Fornecedores existeFornecedor = fornecedorRepository.findById(fornecedoresDTO.getId())
				.orElse(null);
		existeFornecedor.setNmFornecedor(fornecedoresDTO.getNmFornecedor());
		existeFornecedor.setNuCnpj(fornecedoresDTO.getNuCnpj());
		existeFornecedor.setNuTelefone(fornecedoresDTO.getNuTelefone());
		existeFornecedor.setRamoSetorId(fornecedoresDTO.getRamoSetorId());
		existeFornecedor.setInAtivo(fornecedoresDTO.getInAtivo());
		existeFornecedor.setUsuarioIdAtualiza(fornecedoresDTO.getUsuarioIdAtualiza());
		existeFornecedor.setDtUltAtualiza(this.dataLocal);
		return fornecedorRepository.save(existeFornecedor);
	}
	
	public Fornecedores inativarFornecedor(Fornecedores fornecedoresDTO) {
		Fornecedores existeFornecedor = fornecedorRepository.findById(fornecedoresDTO.getId())
				.orElse(null);
		existeFornecedor.setNmFornecedor(fornecedoresDTO.getNmFornecedor());
		existeFornecedor.setNuCnpj(fornecedoresDTO.getNuCnpj());
		existeFornecedor.setNuTelefone(fornecedoresDTO.getNuTelefone());
		existeFornecedor.setInAtivo(fornecedoresDTO.getInAtivo());
		existeFornecedor.setUsuarioIdAtualiza(fornecedoresDTO.getUsuarioIdAtualiza());
		existeFornecedor.setUsuarioIdAtualiza(fornecedoresDTO.getUsuarioIdAtualiza());
		return fornecedorRepository.save(existeFornecedor);
	}

	public String deleleFornecedor(int id) {
		fornecedorRepository.deleteById(id);
		return "Cadastro removido com sucesso!" + id;
	}

	public Page<FornecedoresRet> buscarFornecedorPageSort( String nmFornecedor, String nuCnpj, String nuTelefone, Integer ramoSetorId, String nmRamoSetor, Short inAtivo,
			Integer page, Integer size) {

		//Pageable paging = PageRequest.of(page, size, Sort.by("inAtivo").descending());
		

		Page<FornecedoresRet> pageFornecedor;

		if ((inAtivo != null)  && (nmFornecedor == null))
			pageFornecedor = fornecedorRepository.findByInAtivo(inAtivo, null);
		
		else if ((inAtivo != null) && (nmFornecedor == null))
			pageFornecedor = fornecedorRepository.findByInAtivoAndNmFornecedorContaining(inAtivo,nmFornecedor, null);
		

		else if ((inAtivo != null) && (nmFornecedor != null))
			pageFornecedor = fornecedorRepository.findByInAtivoAndNmFornecedorContaining(inAtivo, nmFornecedor, null);
		
		else if ((nmFornecedor != null) && (inAtivo == null))
			pageFornecedor = fornecedorRepository.findByNmFornecedorContaining(nmFornecedor, null);
		
		else if ((nmFornecedor != null) && (inAtivo != null))
			pageFornecedor = fornecedorRepository.findByInAtivoAndNmFornecedorContaining(inAtivo,
					nmFornecedor, null);
		else
			pageFornecedor = fornecedorRepository.listarFornecedores(null);

		return pageFornecedor;
	}
	
  // combo ramo (Setores)
	public List<RamoSetoresRet> getRamoSetores()
	{
		return fornecedorRepository.getRamoSetores();
	}

    public Page<FornecedoresRet> listarFornecedores() {
    	return this.fornecedorRepository.listarFornecedores(null);
	}
	    
	    
}
