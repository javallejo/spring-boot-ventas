package com.cuasatar.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuasatar.ventas.dto.ProductoDisponibleDTO;
import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService{
	
	
	@Autowired	
	ProductoRepository productoRepository;

	@Override
	public Iterable<Producto> getAllProducts() {

		return productoRepository.findAll();
	}

	@Override
	public Producto createProduct(Producto product) throws Exception {

		product = productoRepository.save(product);	
		return product;
	}

	@Override
	public Producto getProductById(Long id) throws Exception {
		return productoRepository.findById(id).orElseThrow(() -> new Exception("El Id del producto no existe."));
	}

	@Override
	public Producto updateProduct(Producto product) throws Exception {
		return productoRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) throws Exception {
		Producto product  = getProductById(id);
		productoRepository.delete(product);
		
	}

	@Override
	public List<ProductoDisponibleDTO> fetchProductoDisponibleActiveList() throws Exception {

		return productoRepository.fetchProductoDisponibleActiveList();
	}

	@Override
	public Iterable<Producto> getProductListById(Iterable<Long> id) {

		return productoRepository.findAllById(id);
	}
	
	
}
