package com.cuasatar.ventas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.cuasatar.ventas.entity.DetalleVentas;
import com.cuasatar.ventas.entity.Ventas;



public interface DetalleVentasRepository extends CrudRepository<DetalleVentas, Long> {
	
	@Query("SELECT dv.id FROM DetalleVentas dv WHERE dv.ventas = ?1")
	public List<Long> findDetailSalesBySales(Ventas venta);

}
