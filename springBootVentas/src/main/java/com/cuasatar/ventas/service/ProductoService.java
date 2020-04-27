package com.cuasatar.ventas.service;

import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.exception.UsernameOrIdNotFound;

public interface ProductoService {
	public Iterable<Producto> getAllProducts();
	public Producto createProduct(Producto product) throws Exception;
	public Producto getProductById(Long id) throws Exception;
	public Producto updateProduct(Producto product) throws Exception;
	public void deleteProduct(Long id) throws Exception;

}
