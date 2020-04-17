package com.cuasatar.ventas.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cuasatar.ventas.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Optional<Usuario> findByNombreusuario(String username);
	public Optional<Usuario> findByCorreo(String correo);
}
