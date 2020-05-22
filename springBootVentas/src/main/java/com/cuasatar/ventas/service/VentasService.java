package com.cuasatar.ventas.service;


import com.cuasatar.ventas.dto.VentasDTO;
import com.cuasatar.ventas.entity.Ventas;

public interface VentasService {
	public String GenerarSerie() throws Exception;
	
	public VentasDTO fetchNumeroSerieVentas() throws Exception;
	
	public Ventas createSales(Ventas sales) throws Exception;
	
	public Ventas getSalesById(Long id) throws Exception;
	
	public Iterable<Ventas> getAllSales() throws Exception;
	
	
}
