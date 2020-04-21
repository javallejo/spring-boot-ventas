package com.cuasatar.ventas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired	
	UsuarioRepository usuarioRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	
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
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		
		if (checkUsernameAvailable(user) && checkPasswordValid(user) &&  checkEmailAvailable(user)) {
			
			/*modificar el password para que sea seguro*/	
			user.setContrasena(bCryptPasswordEncoder.encode(user.getContrasena()));
			/*modificar el password para que sea seguro*/	
			user = usuarioRepository.save(user);
			
			
		}
		return user;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws Exception {
		Usuario user = getUserById(id);
		usuarioRepository.delete(user);
	}

	@Override
	public Usuario changePassword(ChangePasswordForm form) throws Exception {
		Usuario user = getUserById(form.getId());

		
		
		
		/*encoder.matches("123456", passwd)*/
		
		if ( !isLoggedUserADMIN() && ! passwordEncoder.matches(form.getCurrentPassword(), user.getContrasena())) {
		
		/*if ( !isLoggedUserADMIN() && ! user.getContrasena().equals( form.getCurrentPassword())) {*/
			throw new Exception ("Current Password invalido.");
		}
		
		/*if( user.getContrasena().equals(form.getNewPassword())) {*/
		if(passwordEncoder.matches(form.getNewPassword(), user.getContrasena())) {
			throw new Exception ("Nuevo debe ser diferente al password actual.");
		}
		
		if( !form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception ("Nuevo Password y Confirm Password no coinciden.");
		}
		
		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		user.setContrasena(encodePassword);
		return usuarioRepository.save(user);
	}
	
	public boolean isLoggedUserADMIN(){
		 return loggedUserHasRole("ROLE_ADMIN");
		}

		public boolean loggedUserHasRole(String role) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails loggedUser = null;
			Object roles = null; 
			if (principal instanceof UserDetails) {
				loggedUser = (UserDetails) principal;
			
				roles = loggedUser.getAuthorities().stream()
						.filter(x -> role.equals(x.getAuthority() ))      
						.findFirst().orElse(null); //loggedUser = null;
			}
			return roles != null ?true :false;
		}
	
	

	

}
