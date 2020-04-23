package com.cuasatar.ventas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.cuasatar.ventas.entity.Roles;
import com.cuasatar.ventas.entity.Usuario;

public interface RolesRepository extends CrudRepository<Roles, Long> {
	public Iterable<Roles> findByDescripcion(String descripcion);
}
