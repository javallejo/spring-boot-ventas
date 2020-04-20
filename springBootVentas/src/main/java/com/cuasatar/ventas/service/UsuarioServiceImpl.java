package com.cuasatar.ventas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired	
	UsuarioRepository usuarioRepository;
	
	@Override
	public Iterable<Usuario> getAllUsers() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}
	
	private boolean checkUsernameAvailable(Usuario user) throws Exception {
		Optional<Usuario> userFound = usuarioRepository.findByNombreusuario(user.getNombreusuario());
		if (userFound.isPresent()) {
			throw new Exception("Nombre de usuario no disponible");
		}
		return true;
	}
	
	private boolean checkEmailAvailable(Usuario user) throws Exception {
		Optional<Usuario> emailFound = usuarioRepository.findByCorreo(user.getCorreo());
		if (emailFound.isPresent()) {
			throw new Exception("Correo no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(Usuario user) throws Exception {
		if (user.getConfirmarcontrasena() == null || user.getConfirmarcontrasena().isEmpty()) {
			throw new Exception("Confirmar contraseña es obligatorio");
		}
		
		if ( !user.getContrasena().equals(user.getConfirmarcontrasena())) {
			throw new Exception("Contraseña y confirmar contraseña no son iguales");
		}
		return true;
	}
	
	@Override
	public Usuario getUserById(Long id) throws Exception {
		return usuarioRepository.findById(id).orElseThrow(() -> new Exception("El usuario para editar no existe."));
	}
	@Override
	public Usuario updateUser(Usuario fromUser) throws Exception {
		Usuario toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return usuarioRepository.save(toUser);
	}
	
	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(Usuario from,Usuario to) {
		to.setNombreusuario(from.getNombreusuario());
		to.setNombre(from.getNombre());
		to.setApellido(from.getApellido());
		to.setCorreo(from.getCorreo());
		to.setRoles(from.getRoles());
	}
	

	@Override
	public Usuario createUser(Usuario user) throws Exception {
		// TODO Auto-generated method stub
		if (checkUsernameAvailable(user) && checkPasswordValid(user) &&  checkEmailAvailable(user)) {
			user = usuarioRepository.save(user);
		}
		return user;
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		// TODO Auto-generated method stub
		Usuario user = usuarioRepository.findById(id)
			/*	.orElseThrow(() -> new Exception("Usuario no hallado para eliminar"+this.getClass().getName()));*/
				.orElseThrow(() -> new Exception("Usuario no hallado para eliminar"));
		usuarioRepository.deleteById(id);
	}

}
