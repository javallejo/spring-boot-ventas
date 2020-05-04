package com.cuasatar.ventas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.entity.Ventas;
import com.cuasatar.ventas.repository.VentasRepository;

@Service
public class VentasServiceImpl implements VentasService {

	@Autowired
	VentasRepository ventasRepository;
	
	@Autowired
	ProductoService productoService;
	
	@Override
	public String GenerarSerie() throws Exception {
		String numeroserie="";
		
		numeroserie=fetchNumeroSerieVentas();

        return numeroserie;
	}

	@Override
	public String fetchNumeroSerieVentas() throws Exception {

		return ventasRepository.fetchNumeroSerieVentas();
	}

	@Override
	public Ventas createSales(Ventas sales) throws Exception {

		sales  = ventasRepository.save(sales);
		
		return sales;
	}

	

}
