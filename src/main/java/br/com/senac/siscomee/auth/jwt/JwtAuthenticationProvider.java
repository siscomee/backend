package br.com.senac.siscomee.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.senac.siscomee.auth.jwt.model.User;
import br.com.senac.siscomee.auth.jwt.service.UserAuthenticationService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = authenticationService.authenticateToken((String) authentication.getCredentials());
        return new JwtAuthenticatedUserToken(user.getUsername(), user.getUserRoles());
    }

    /*
     Returns true if this AuthenticationProvider supports the indicated Authentication object.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthToken.class.equals(aClass);
    }
}
