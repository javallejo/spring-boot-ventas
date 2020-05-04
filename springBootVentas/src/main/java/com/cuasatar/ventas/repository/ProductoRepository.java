package com.cuasatar.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.cuasatar.ventas.dto.ProductoDisponibleDTO;
import com.cuasatar.ventas.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

	@Query("select new com.cuasatar.ventas.dto.ProductoDisponibleDTO(p.id) from  Producto p inner join Disponible d on d.id=p.disponible where d.descripcion='AVAILABLE_YES' order by p.nombre asc")
	public List<ProductoDisponibleDTO> fetchProductoDisponibleActiveList();
	
}
