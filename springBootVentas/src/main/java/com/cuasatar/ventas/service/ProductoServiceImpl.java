package com.cuasatar.ventas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Producto ToProduct = getProductById(product.getId());
		return productoRepository.save(ToProduct);
	}

	@Override
	public void deleteProduct(Long id) throws Exception {
		Producto product  = getProductById(id);
		productoRepository.delete(product);
		
	}

}
