package com.cuasatar.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.UsuarioRepository;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Autowired	
	UsuarioRepository usuarioRepository;
	
	 @Override
	    public Authentication getAuthentication() {
	        return SecurityContextHolder.getContext().getAuthentication();
	    }

	@Override
	public Usuario getLoggedUsuario() throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		
		Usuario myUser = usuarioRepository
				.findByNombreusuario(loggedUser.getUsername()).orElseThrow(() -> new Exception("Error obteniendo el usuario logeado desde la sesion."));
		
		return myUser;
	}

}
