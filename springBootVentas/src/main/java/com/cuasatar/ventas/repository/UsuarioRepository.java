package com.cuasatar.ventas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cuasatar.ventas.dto.UsuarioRolesDTO;
import com.cuasatar.ventas.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Optional<Usuario> findByNombreusuario(String username);
	public Optional<Usuario> findByCorreo(String correo);
	
	/*Obtener lista de usuarios por id y rol role_user*/
	@Query("select new com.cuasatar.ventas.dto.UsuarioRolesDTO(u.id) from  Usuario u inner join UsuarioRoles ur on ur.usuarioId=u.id inner join Roles r on r.id=ur.rolId where r.descripcion='ROLE_USER'")
	public List<UsuarioRolesDTO> fetchUsuarioRolesUserInnerJoin();
	
	
	
	
}
