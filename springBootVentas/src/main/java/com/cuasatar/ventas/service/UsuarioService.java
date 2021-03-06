package com.cuasatar.ventas.service;

import java.util.List;



import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.dto.UsuarioRolesDTO;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.exception.UsernameOrIdNotFound;

public interface UsuarioService {
	
	public Iterable<Usuario> getAllUsers();
	public Usuario createUser(Usuario user) throws Exception;
	public Usuario getUserById(Long id) throws Exception;
	public Usuario updateUser(Usuario user) throws Exception;
	public void deleteUser(Long id) throws UsernameOrIdNotFound;
	public Usuario changePassword(ChangePasswordForm form) throws Exception;
	public Iterable<Usuario> getUserListById(Iterable<Long> id);

	public List<UsuarioRolesDTO> fetchUsuarioRolesUserInnerJoin() throws Exception;
	
	public Usuario getUserLog() throws Exception;

	
}
