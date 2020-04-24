package com.cuasatar.ventas.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7325429897476779224L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClienteIdGenerator")	
	@SequenceGenerator(name = "ClienteIdGenerator", sequenceName = "cliente_pk_seq", allocationSize = 1)		
	private Long id;
	@Column
	@Pattern(regexp = "[0-9]+", message="El dni solo debe llevar numeros") 	
	@Size(min = 8, max = 8, message="El dni debe contener solo 8 digitos")
	@NotBlank
	private String dni;/*size 8*/
	@Column(unique = false, nullable = false)
	@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ ]+", message="El nombre solo puede contener letras en español") 
	@NotBlank
	private String nombres;
	@Column(unique = false, nullable = false)
	@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ0-9# ]+", message="Escriba una direccion valida") 
	@NotBlank
	private String direccion;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="cliente_estado", 
    			joinColumns={@JoinColumn(name="cliente_id")}, 
    			inverseJoinColumns={@JoinColumn(name="estado_id")})
	private Set<Estado> estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
	


	public Set<Estado> getEstado() {
		return estado;
	}

	public void setEstado(Set<Estado> estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
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
		Cliente other = (Cliente) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", nombres=" + nombres + ", direccion=" + direccion + ", estado="
				+ estado + "]";
	}

	
	


	

	 


}
