package com.cuasatar.ventas.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.service.ProductoService;

@RestController
public class ProductoRestController {
	
	@Autowired
	ProductoService productoService;
	
	@RequestMapping(value="/ventas/producto/getProduct", method=RequestMethod.GET)
	public ResponseEntity<Object> obtenerProducto(@RequestParam long id) throws Exception {
		Producto producto= new Producto();
		
		producto=productoService.getProductById(id);
		/*productoEscogido(producto);*/
		return new ResponseEntity<> (producto,HttpStatus.OK);
		
	}
	
	/*public Producto productoEscogido(Producto p) {
		return p;
	}*/

}
