package com.cuasatar.ventas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cliente_estado")
public class ClienteEstado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 456985438855092649L;
	
	@Id
	@Column(name="cliente_id")
	private Long clienteId;
	@Id
	@Column(name="estado_id")
	private Long estadoId;
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
		result = prime * result + ((estadoId == null) ? 0 : estadoId.hashCode());
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
		ClienteEstado other = (ClienteEstado) obj;
		if (clienteId == null) {
			if (other.clienteId != null)
				return false;
		} else if (!clienteId.equals(other.clienteId))
			return false;
		if (estadoId == null) {
			if (other.estadoId != null)
				return false;
		} else if (!estadoId.equals(other.estadoId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ClienteEstado [clienteId=" + clienteId + ", estadoId=" + estadoId + "]";
	}
	
	

}
