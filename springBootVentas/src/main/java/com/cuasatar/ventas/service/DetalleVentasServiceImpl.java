package com.cuasatar.ventas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.entity.DetalleVentas;
import com.cuasatar.ventas.entity.Ventas;
import com.cuasatar.ventas.repository.DetalleVentasRepository;


@Service
public class DetalleVentasServiceImpl implements DetalleVentasService {

	
	@Autowired
	DetalleVentasRepository detalleVentasRepository;
	
	

	



	@Override
	public Iterable<DetalleVentas> getDetailBySales(Ventas v) throws Exception {
		// TODO Auto-generated method stub
		
		
		List<Long> idVentas = new ArrayList<>();
		Iterable<Long> idVentasIt=null;
		
		idVentas=detalleVentasRepository.findDetailSalesBySales(v);
		
		idVentasIt=(Iterable<Long>) idVentas;
		
		/*
		Long id=v.getId();
		List<Long> idVentas = new ArrayList<>();
		Iterable<Long> idVentasIt=null;
		idVentas.add(id);
		idVentasIt=(Iterable<Long>) idVentas;
		detalleVentasRepository.findAllById(ids);
		*/
		
		
		return detalleVentasRepository.findAllById(idVentasIt);
	}







	@Override
	public Iterable<DetalleVentas> getAllSalesDetails() throws Exception {
		// TODO Auto-generated method stub
		return detalleVentasRepository.findAll();
	}

}
