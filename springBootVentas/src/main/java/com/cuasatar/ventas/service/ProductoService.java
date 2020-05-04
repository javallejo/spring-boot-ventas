package com.cuasatar.ventas.service;

import java.util.List;


import com.cuasatar.ventas.dto.ProductoDisponibleDTO;
import com.cuasatar.ventas.entity.Producto;


public interface ProductoService {
	public Iterable<Producto> getAllProducts();
	public Producto createProduct(Producto product) throws Exception;
	public Producto getProductById(Long id) throws Exception;
	public Producto updateProduct(Producto product) throws Exception;
	public void deleteProduct(Long id) throws Exception;
	
	
	public Iterable<Producto> getProductListById(Iterable<Long> id);
	
	public List<ProductoDisponibleDTO> fetchProductoDisponibleActiveList() throws Exception;
	


}
