package br.com.senac.siscomee.auth.jwt.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.siscomee.auth.jwt.model.AuthenticationResponse;
import br.com.senac.siscomee.auth.jwt.model.UserCredentials;
import br.com.senac.siscomee.auth.jwt.service.JwtService;
import br.com.senac.siscomee.auth.jwt.service.UserAuthenticationService;
import br.com.senac.siscomee.model.dto.UsuarioDTO;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	private UserAuthenticationService authenticationService;
	
	@Autowired
	private JwtService jwtService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public AuthenticationResponse userLogin(@RequestBody UserCredentials userCredentials) {

		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		try {
			if (userCredentials == null
					|| (userCredentials.getUsername() == null || userCredentials.getPassword() == null)) {
				authenticationResponse.setSucesso(false);
				authenticationResponse.setMsgRetorno("As credenciais necessárias para o login estão faltando.");
				return authenticationResponse;
			}

			UsuarioDTO usuario = authenticationService.autenticarUsuario(userCredentials.getUsername(),userCredentials.getPassword());

			if (usuario != null) {
				authenticationResponse.setUsername(userCredentials.getUsername());
				authenticationResponse.setToken(usuario.getAccessToken());
				authenticationResponse.setSucesso(true);
				authenticationResponse.setSituacao(true);
				authenticationResponse.setMsgRetorno("Operação realizada com sucesso.");
			} else {
				authenticationResponse.setUsername(userCredentials.getUsername());
				authenticationResponse.setToken("");
				authenticationResponse.setSucesso(false);
				authenticationResponse.setMsgRetorno(
						String.format(" Não foi possível autenticar o usuário [%s] ", userCredentials.getUsername()));
				return authenticationResponse;
			}

		} catch (Exception e) {

		}

		return authenticationResponse;
	}
	

}
