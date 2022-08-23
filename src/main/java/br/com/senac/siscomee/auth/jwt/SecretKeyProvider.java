package br.com.senac.siscomee.auth.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class SecretKeyProvider {
	
    public byte[] getKey() throws IOException {
    	
    	return "m1r3st4p1".getBytes();
    	
    }
    
    
}
