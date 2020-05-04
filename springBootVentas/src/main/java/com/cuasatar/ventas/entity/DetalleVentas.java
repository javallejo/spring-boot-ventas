package com.cuasatar.ventas.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class DetalleVentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8654894465892611247L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DetalleVentasIdGenerator")	
	@SequenceGenerator(name = "DetalleVentas", sequenceName = "detalleventas_pk_seq", allocationSize = 1)	
	private Long id;
	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="ventas_id",referencedColumnName="id",nullable=false)
	private Ventas ventas;
	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="producto_id",referencedColumnName="id",nullable=false)
	private Producto producto;
	@Column
	private int cantidad;
	@Column
	private double precioventa;
	
	
	
	
	public DetalleVentas() {
		super();
	}
	
	
	public DetalleVentas(Long id, Ventas ventas, Producto producto, int cantidad, double precioventa) {
		super();
		this.id = id;
		this.ventas = ventas;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioventa = precioventa;
	}
	
	


	public DetalleVentas(Ventas ventas, Producto producto, int cantidad, double precioventa) {
		super();
		this.ventas = ventas;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioventa = precioventa;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Ventas getVentas() {
		return ventas;
	}
	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precioventa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		result = prime * result + ((ventas == null) ? 0 : ventas.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleVentas other = (DetalleVentas) obj;
		if (cantidad != other.cantidad)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(precioventa) != Double.doubleToLongBits(other.precioventa))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (ventas == null) {
			if (other.ventas != null)
				return false;
		} else if (!ventas.equals(other.ventas))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DetalleVentas [id=" + id + ", ventas=" + ventas + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", precioventa=" + precioventa + "]";
	}
	

}
