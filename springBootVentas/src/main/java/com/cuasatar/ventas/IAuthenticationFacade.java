package com.cuasatar.ventas;

import org.springframework.security.core.Authentication;

import com.cuasatar.ventas.entity.Usuario;

public interface IAuthenticationFacade {
	Authentication getAuthentication();
	Usuario getLoggedUsuario() throws Exception;
}


