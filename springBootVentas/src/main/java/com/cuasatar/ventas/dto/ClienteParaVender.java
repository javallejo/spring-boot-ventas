package com.cuasatar.ventas.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.entity.Estado;

public class ClienteParaVender extends Cliente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4428284493853607871L;
	private Iterable<Cliente> clienteList;
	
	private Long id;

	private String dni;/*size 8*/

	private String nombres;

	private String direccion;
	

	
	
	

	public ClienteParaVender() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteParaVender(Long id,
			@Pattern(regexp = "[0-9]+", message = "El dni solo debe llevar numeros") @Size(min = 8, max = 8, message = "El dni debe contener solo 8 digitos") @NotBlank String dni,
			@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ ]+", message = "El nombre solo puede contener letras en español") @NotBlank String nombres,
			@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ0-9# ]+", message = "Escriba una direccion valida") @NotBlank String direccion) {
		super(id, dni, nombres, direccion);
		// TODO Auto-generated constructor stub
	}

	public ClienteParaVender(
			@Pattern(regexp = "[0-9]+", message = "El dni solo debe llevar numeros") @Size(min = 8, max = 8, message = "El dni debe contener solo 8 digitos") @NotBlank String dni,
			@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ ]+", message = "El nombre solo puede contener letras en español") @NotBlank String nombres,
			@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ0-9# ]+", message = "Escriba una direccion valida") @NotBlank String direccion) {
		super(dni, nombres, direccion);
		// TODO Auto-generated constructor stub
	}

	
	
	public ClienteParaVender(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Iterable<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(Iterable<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	@Override
	public String toString() {
		return "ClienteParaVender [clienteList=" + clienteList + ", id=" + id + "]";
	}
	
	
	
	

}
