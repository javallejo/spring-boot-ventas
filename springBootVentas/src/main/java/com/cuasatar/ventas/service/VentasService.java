package com.cuasatar.ventas.service;


import java.io.IOException;
import java.sql.SQLException;

import com.cuasatar.ventas.dto.VentasDTO;
import com.cuasatar.ventas.entity.Ventas;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface VentasService {
	public String GenerarSerie() throws Exception;
	
	public VentasDTO fetchNumeroSerieVentas() throws Exception;
	
	public Ventas createSales(Ventas sales) throws Exception;
	
	public Ventas getSalesById(Long id) throws Exception;
	
	public Iterable<Ventas> getAllSales() throws Exception;
	
	public JasperPrint exportPdfFile(Long id) throws SQLException, JRException, IOException;
	
	
}
