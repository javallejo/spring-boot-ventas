package com.cuasatar.ventas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cuasatar.ventas.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Optional<Usuario> findByNombreusuario(String username);
	public Optional<Usuario> findByCorreo(String correo);
	
	/*Obtener lista de usuarios por id y rol role_user*/
	/*@Query("SELECT new com.cuasatar.entity.Usuario(u.id) from  Usuario u"
			+" inner join usuario_roles ur on ur.usuario_id=u.id"
			+" inner join Roles r on r.id=ur.rol_id")
	public List<Usuario> fetchUsuarioRolesUserInnerJoin();
	*/
}
