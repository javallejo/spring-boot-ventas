package com.cuasatar.ventas.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@Entity
public class Producto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1380090063857786770L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductoIdGenerator")	
	@SequenceGenerator(name = "ProductoIdGenerator", sequenceName = "producto_pk_seq", allocationSize = 1)			
	Long id;
	@Column
	@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ0-9 ]+", message="El nombre solo puede contener letras en español y numeros") 	
    @NotBlank
	String nombre;
	@Column
	@Min(1)
    double precio;
	@Column
	@Min(1)
    int cantidad;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="disponible_id",referencedColumnName="id",nullable=false)
	private Disponible disponible;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    
	public Disponible getDisponible() {
		return disponible;
	}
	public void setDisponible(Disponible disponible) {
		this.disponible = disponible;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((disponible == null) ? 0 : disponible.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Producto other = (Producto) obj;
		if (cantidad != other.cantidad)
			return false;
		if (disponible == null) {
			if (other.disponible != null)
				return false;
		} else if (!disponible.equals(other.disponible))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad
				+ ", disponible=" + disponible + "]";
	}
	
	
    


}
