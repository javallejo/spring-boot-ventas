package com.cuasatar.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.service.ProductoService;


@SpringBootApplication
public class SpringBootVentasApplication {

	@Autowired
	ProductoService productoService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootVentasApplication.class, args);
	}
	
	
	

}
