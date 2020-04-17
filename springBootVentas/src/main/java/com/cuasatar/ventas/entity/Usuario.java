package com.cuasatar.ventas.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@Entity
public class Usuario implements Serializable{

	/**
	 * comentario
	 */
	private static final long serialVersionUID = -821187118344256214L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioIdGenerator")	
	@SequenceGenerator(name = "UsuarioIdGenerator", sequenceName = "usuario_pk_seq", allocationSize = 1)	
	private Long id;
	@Column
	@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ ]+", message="El nombre solo puede contener letras en español") 
	@NotBlank
	private String nombre;
	@Column
	@Pattern(regexp = "[A-Za-zñÑáéíóúÁÉÍÓÚ ]+", message="El apellido solo puede contener letras en español") 
	@NotBlank
	private String apellido;
	@Column(unique=true)
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message="Ingrese un correo válido") 
	@NotBlank
	private String correo;
	@Column(unique=true)
	@Pattern(regexp = "[A-Za-z]+", message="El nombre de usuario no debe llevar acentos, eñes ni espacios") 
	@NotBlank
	private String nombreusuario;
	@Column
	/*@Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})", message="La contraseña debe tener al menos una mayúscula, una minúscula, un número, un caracter especial (@#$%) y tener entre 6 a 16 caracteres")*/ 
	@NotBlank
	private String contrasena;

	@Transient
	/*@Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})", message="La contraseña debe tener al menos una mayúscula, una minúscula, un número, un caracter especial (@#$%) y tener entre 6 a 16 caracteres")*/ 
	@NotBlank
	private String confirmarcontrasena;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_roles",
			joinColumns=@JoinColumn(name="usuario_id"),
			inverseJoinColumns=@JoinColumn(name="rol_id"))
	private Set<Roles> roles;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getConfirmarcontrasena() {
		return confirmarcontrasena;
	}

	public void setConfirmarcontrasena(String confirmarcontrasena) {
		this.confirmarcontrasena = confirmarcontrasena;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((confirmarcontrasena == null) ? 0 : confirmarcontrasena.hashCode());
		result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreusuario == null) ? 0 : nombreusuario.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		Usuario other = (Usuario) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (confirmarcontrasena == null) {
			if (other.confirmarcontrasena != null)
				return false;
		} else if (!confirmarcontrasena.equals(other.confirmarcontrasena))
			return false;
		if (contrasena == null) {
			if (other.contrasena != null)
				return false;
		} else if (!contrasena.equals(other.contrasena))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
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
		if (nombreusuario == null) {
			if (other.nombreusuario != null)
				return false;
		} else if (!nombreusuario.equals(other.nombreusuario))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", nombreusuario=" + nombreusuario + ", contrasena=" + contrasena + ", confirmarcontrasena="
				+ confirmarcontrasena + ", roles=" + roles + "]";
	}
	
	
}
