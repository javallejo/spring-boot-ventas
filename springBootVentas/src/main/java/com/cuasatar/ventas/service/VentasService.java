package com.cuasatar.ventas.service;

import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.dto.VentasDTO;
import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.entity.Ventas;

public interface VentasService {
	public String GenerarSerie() throws Exception;
	
	public VentasDTO fetchNumeroSerieVentas() throws Exception;
	
	public Ventas createSales(Ventas sales) throws Exception;
	
	
}
