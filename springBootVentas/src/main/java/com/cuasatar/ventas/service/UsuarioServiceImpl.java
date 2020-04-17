package com.cuasatar.ventas.service;

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

}
