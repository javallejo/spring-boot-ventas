package com.cuasatar.ventas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ventas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4176389635820037882L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VentasIdGenerator")	
	@SequenceGenerator(name = "VentasIdGenerator", sequenceName = "ventas_pk_seq", allocationSize = 1)	
	private Long id;	
	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="cliente_id",referencedColumnName="id",nullable=false)
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="usuario_id",referencedColumnName="id",nullable=false)
	private Usuario usuario;
	@Column
	private String numeroserie;
	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaventas;
	@Column
	private Double monto;
	/*
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ventas_estadoventa",
			joinColumns=@JoinColumn(name="ventas_id"),
			inverseJoinColumns=@JoinColumn(name="estadoventas_id"))
	private Set<EstadoVenta> estadoventa;
	*/
	
	public Ventas() {
		super();
	}
	
	
	


	public Ventas(Cliente cliente, Usuario usuario, String numeroserie, Date fechaventas, Double monto) {
		super();
		this.cliente = cliente;
		this.usuario = usuario;
		this.numeroserie = numeroserie;
		this.fechaventas = fechaventas;
		this.monto = monto;
	}





	public Ventas(Long id, Cliente cliente, Usuario usuario, String numeroserie, Date fechaventas, Double monto) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.usuario = usuario;
		this.numeroserie = numeroserie;
		this.fechaventas = fechaventas;
		this.monto = monto;
	}





	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNumeroserie() {
		return numeroserie;
	}
	public void setNumeroserie(String numeroserie) {
		this.numeroserie = numeroserie;
	}
	public Date getFechaventas() {
		return fechaventas;
	}
	public void setFechaventas(Date fechaventas) {
		this.fechaventas = fechaventas;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((fechaventas == null) ? 0 : fechaventas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((numeroserie == null) ? 0 : numeroserie.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Ventas other = (Ventas) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (fechaventas == null) {
			if (other.fechaventas != null)
				return false;
		} else if (!fechaventas.equals(other.fechaventas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
		if (numeroserie == null) {
			if (other.numeroserie != null)
				return false;
		} else if (!numeroserie.equals(other.numeroserie))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "Ventas [id=" + id + ", cliente=" + cliente + ", usuario=" + usuario + ", numeroserie=" + numeroserie
				+ ", fechaventas=" + fechaventas + ", monto=" + monto + "]";
	}







	
	

}
