package br.com.senac.siscomee.model.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.senac.siscomee.model.dto.UsuarioDTO;

@Service
public class UsuarioService {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UsuarioService.class);

	public UsuarioDTO findByLoginSenha(String login,String senha) throws Exception {
		throw new Exception("Implementar consulta no repository e etc ...");
	}
	
}
