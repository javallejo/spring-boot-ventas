package com.cuasatar.ventas.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.dto.VentasDTO;
import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.entity.Ventas;
import com.cuasatar.ventas.repository.VentasRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class VentasServiceImpl implements VentasService {

	@Autowired
	VentasRepository ventasRepository;
	
	@Autowired
	ProductoService productoService;
	
	 @Autowired
	 @Qualifier("jdbcTemplate")
	 private JdbcTemplate jdbcTemplate;
	 
	 @Autowired
	 private ResourceLoader resourceLoader;
	
	@Override
	public String GenerarSerie() throws Exception {
		
		VentasDTO vdto=new VentasDTO();
		vdto=fetchNumeroSerieVentas();
		String numeroserie="";
		
		numeroserie=vdto.getNumeroserie();

        return numeroserie;
	}

	@Override
	public VentasDTO fetchNumeroSerieVentas() throws Exception {

		return ventasRepository.fetchNumeroSerieVentas();
	}

	@Override
	public Ventas createSales(Ventas sales) throws Exception {

		sales  = ventasRepository.save(sales);
		
		return sales;
	}

	@Override
	public Iterable<Ventas> getAllSales() throws Exception {
		return ventasRepository.findAll();
	}

	@Override
	public Ventas getSalesById(Long id) throws Exception {

		return ventasRepository.findById(id).orElseThrow(() -> new Exception("El Id de la venta no existe."));

	}

	@Override
	public JasperPrint exportPdfFile(Long id) throws SQLException, JRException, IOException {
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		String path = resourceLoader.getResource("classpath:sales.jrxml").getURI().getPath();
		String pathsubReport = resourceLoader.getResource("classpath:salesdetails.jrxml").getURI().getPath();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		JasperReport jasperSubReport = JasperCompileManager.compileReport(pathsubReport);
		// Parameters for report
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("SubReportParam", jasperSubReport);
		 parameters.put("ventasid", id);
		 JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
		  return print;
		 
	}

	

}
