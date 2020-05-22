package com.cuasatar.ventas.service;

import com.cuasatar.ventas.entity.DetalleVentas;
import com.cuasatar.ventas.entity.Ventas;



public interface DetalleVentasService {

	
	public Iterable<DetalleVentas> getDetailBySales(Ventas v) throws Exception;
	
	
	public Iterable<DetalleVentas> getAllSalesDetails() throws Exception;
}
