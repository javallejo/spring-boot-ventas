package com.cuasatar.ventas.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.cuasatar.ventas.entity.Disponible;
import com.cuasatar.ventas.entity.Producto;

public class ProductoParaVenderDTO extends Producto{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7146628864474796592L;
	
	private Double cantidadProducto;
	
	




	public ProductoParaVenderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}






	public ProductoParaVenderDTO(Long id,
			@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ0-9 ]+", message = "El nombre solo puede contener letras en español y numeros") @NotBlank String nombre,
			@Min(1) double precio, @Min(1) int cantidad,Double cantidadProducto) {
		super(id, nombre, precio, cantidad);
		this.cantidadProducto = cantidadProducto;
		// TODO Auto-generated constructor stub
	}






	public ProductoParaVenderDTO(
			@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ0-9 ]+", message = "El nombre solo puede contener letras en español y numeros") @NotBlank String nombre,
			@Min(1) double precio, @Min(1) int cantidad,Double cantidadProducto) {
		super(nombre, precio, cantidad);
		this.cantidadProducto = cantidadProducto;
		// TODO Auto-generated constructor stub
	}






	public void aumentarCantidadProducto() {
        this.cantidadProducto++;
    }

	public Double getTotal() {
        return this.getPrecio() * this.cantidadProducto;
    }



	public Double getCantidadProducto() {
		return cantidadProducto;
	}




	public void setCantidadProducto(Double cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
}
