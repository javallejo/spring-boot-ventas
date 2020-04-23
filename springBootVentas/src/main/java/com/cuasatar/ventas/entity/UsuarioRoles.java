package com.cuasatar.ventas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario_roles")
public class UsuarioRoles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2303390991335045747L;
	@Id
	@Column(name="usuario_id")
	private Long usuarioId;
	@Id
	@Column(name="rol_id")
	private Long rolId;
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getRolId() {
		return rolId;
	}
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rolId == null) ? 0 : rolId.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
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
		UsuarioRoles other = (UsuarioRoles) obj;
		if (rolId == null) {
			if (other.rolId != null)
				return false;
		} else if (!rolId.equals(other.rolId))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UsuarioRoles [usuarioId=" + usuarioId + ", rolId=" + rolId + "]";
	}
	
	
	
	
}
