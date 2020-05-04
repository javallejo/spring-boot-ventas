package com.cuasatar.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cuasatar.ventas.dto.ClientesEstadoDTO;
import com.cuasatar.ventas.entity.Ventas;

public interface VentasRepository extends CrudRepository<Ventas, Long> {

	
	@Query("select new com.cuasatar.ventas.dto.VentasDTO(v.numeroserie) from  Ventas v")
	public String fetchNumeroSerieVentas();
}
