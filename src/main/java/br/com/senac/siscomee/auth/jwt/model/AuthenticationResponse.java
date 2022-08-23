package br.com.senac.siscomee.auth.jwt.model;

public class AuthenticationResponse {

    private String username;
    private String token;
    private boolean sucesso = false;
    private String msgRetorno = "";
    private int tentativasInvalidasConsecutivas = 0;
    private boolean situacao = false;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	public int getTentativasInvalidasConsecutivas() {
		return tentativasInvalidasConsecutivas;
	}

	public void setTentativasInvalidasConsecutivas(int tentativasInvalidasConsecutivas) {
		this.tentativasInvalidasConsecutivas = tentativasInvalidasConsecutivas;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
    
    
    
}
