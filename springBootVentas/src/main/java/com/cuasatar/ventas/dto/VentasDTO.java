package com.cuasatar.ventas.dto;

import java.util.Set;

import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.entity.Producto;


public class VentasDTO {
	
	
	private Long id;
	private Integer item;
	private Long idcliente;
	private Long idempleado;
	private Long idproducto;
	private String numeroserie;
	private String descripcionproducto;

	private Double precio;
	private Integer cantidad;
	private Double subtotal;
	private Double monto;
	private String estado;
	
	private Iterable<Cliente> clienteList;
	
	private Iterable<Producto> productoList;
	
	
	

   
    




	

	
	


	public VentasDTO(Long id) {
		super();
		this.id = id;
	}

	public VentasDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public Long getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(Long idempleado) {
		this.idempleado = idempleado;
	}

	public Long getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Long idproducto) {
		this.idproducto = idproducto;
	}



	
	public VentasDTO(String numeroserie) {
		super();
		this.numeroserie = numeroserie;
	}

	public String getNumeroserie() {
		return numeroserie;
	}

	public void setNumeroserie(String numeroserie) {
		this.numeroserie = numeroserie;
	}

	public String getDescripcionproducto() {
		return descripcionproducto;
	}

	public void setDescripcionproducto(String descripcionproducto) {
		this.descripcionproducto = descripcionproducto;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Iterable<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(Iterable<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	public Iterable<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(Iterable<Producto> productoList) {
		this.productoList = productoList;
	}

	

	

	
	
	
	
	
}
