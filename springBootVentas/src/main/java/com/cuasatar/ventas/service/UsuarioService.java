package com.cuasatar.ventas.service;

import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.entity.Usuario;

public interface UsuarioService {
	
	public Iterable<Usuario> getAllUsers();
	public Usuario createUser(Usuario user) throws Exception;
	public Usuario getUserById(Long id) throws Exception;
	public Usuario updateUser(Usuario user) throws Exception;
	public void deleteUser(Long id) throws Exception;
	public Usuario changePassword(ChangePasswordForm form) throws Exception;
	
}
