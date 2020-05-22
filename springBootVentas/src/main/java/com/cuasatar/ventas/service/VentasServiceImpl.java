package com.cuasatar.ventas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.dto.VentasDTO;
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

	

}
