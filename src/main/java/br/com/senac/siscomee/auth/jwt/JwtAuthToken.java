package br.com.senac.siscomee.auth.jwt;

import java.util.Collection;

import javax.security.auth.Subject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


public class JwtAuthToken implements Authentication {

	private static final long serialVersionUID = 1L;
	private String token;
	private String name;

    public JwtAuthToken(String token,String name) {
        this.token = token;
        this.name = name;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAuthenticated() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getPrincipal() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getDetails() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getCredentials() {
        return this.token;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return this.name;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
