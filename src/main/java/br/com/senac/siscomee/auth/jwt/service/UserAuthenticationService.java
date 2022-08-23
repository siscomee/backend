package br.com.senac.siscomee.auth.jwt.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senac.siscomee.model.services.UsuarioService;
import br.com.senac.siscomee.auth.jwt.exception.FalhaNoLogin;
import br.com.senac.siscomee.auth.jwt.exception.JwtAuthenticationException;
import br.com.senac.siscomee.auth.jwt.model.User;
import br.com.senac.siscomee.model.dto.UsuarioDTO;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class UserAuthenticationService {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UsuarioService usuarioService;

	public UsuarioDTO autenticarUsuario(String username, String password) throws FalhaNoLogin, ParseException, IOException {
		boolean isAuthenticated = false;

		UsuarioDTO usuario = null;
		
		try {
			 usuario = usuarioService.findByLoginSenha(username, password);
		}catch (Exception e) {
			// logar , tratar e etc. Várias tentarivas erradas de login podem caracterizar uma tentativa de invasão e etc.
		}
		
		if (usuario != null) {
			isAuthenticated = true;
		}
		
		if (isAuthenticated) {
			try {
				usuario.setAccessToken(jwtService.generateToken(username));
				return usuario;
			} catch (URISyntaxException | IOException e) {
				throw new FalhaNoLogin(e.getMessage());
			}
		}
		throw new FalhaNoLogin(String.format("Não foi possível autenticar o usuário [%s]", username));
	}

	public User authenticateToken(String jwtToken) throws JwtAuthenticationException {

		try {
			String username = jwtService.verifyToken(jwtToken);
			List<String> userRoles = null; /* implementar logica para recuperar roles do usuário*/
			User user = new User();
			user.setUsername(username);
			user.setUserRoles(userRoles);
			return user;
		} catch (IOException | URISyntaxException | ExpiredJwtException e) {
			throw new JwtAuthenticationException(e.getMessage(), e);
		}
	}
	
}
