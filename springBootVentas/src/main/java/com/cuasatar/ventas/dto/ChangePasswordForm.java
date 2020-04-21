package com.cuasatar.ventas.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordForm {
	@NotNull
	private Long id;
	
	@NotBlank(message="Contraseña actual no debe ser vacio")
	private String currentPassword;
	
	@NotBlank(message="Nueva contraseña must no debe ser vacio")
	private String newPassword;
	
	@NotBlank(message="Confirmar contraseña no debe ser vacio")
	private String confirmPassword;

	public ChangePasswordForm() {	}
	
	public ChangePasswordForm(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
