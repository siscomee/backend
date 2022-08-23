package br.com.senac.siscomee.auth.jwt.exception;

public class FalhaNoLogin extends Exception {

	private static final long serialVersionUID = 1L;

	public FalhaNoLogin(String username) {
        super(username);
    }
}
