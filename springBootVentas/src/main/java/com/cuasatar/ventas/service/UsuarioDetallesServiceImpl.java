package com.cuasatar.ventas.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.entity.Roles;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.UsuarioRepository;


@Service
@Transactional
public class UsuarioDetallesServiceImpl implements UserDetailsService {
	
	
	@Autowired
    UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//Buscar nombre de usuario en nuestra base de datos
	  Usuario appUser =usuarioRepository.findByNombreusuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no existe!"));

      Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
	    
      //Crear la lista de los roles/accessos que tienen el usuarios
	    for (Roles role: appUser.getRoles()) {
	        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescripcion());
	            grantList.add(grantedAuthority);
	    }
			
	    //Crear y retornar Objeto de usuario soportado por Spring Security
	    UserDetails user = (UserDetails) new User(appUser.getNombreusuario(), appUser.getContrasena(), grantList);
	    return user;
		
	
	
	}

}
