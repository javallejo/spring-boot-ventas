package com.cuasatar.ventas.service;

import com.cuasatar.ventas.entity.Usuario;

public interface UsuarioService {
	
	public Iterable<Usuario> getAllUsers();
	public Usuario createUser(Usuario user) throws Exception;
	
}
