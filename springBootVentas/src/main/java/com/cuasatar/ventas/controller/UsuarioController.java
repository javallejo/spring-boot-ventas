package com.cuasatar.ventas.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.RolesRepository;
import com.cuasatar.ventas.service.UsuarioService;


@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolesRepository rolesRepository;
	
	
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {		
		model.addAttribute("usuarioFormulario", new Usuario());
		model.addAttribute("usuarioLista",usuarioService.getAllUsers());
		model.addAttribute("roles",rolesRepository.findAll());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("usuarioFormulario")Usuario user, BindingResult result, ModelMap model) {
		String nuevaContrasena="";
		String nuevaContrasenaConfirm="";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		
		if(result.hasErrors()) {			
			model.addAttribute("usuarioFormulario", user);
			model.addAttribute("usuarioLista",usuarioService.getAllUsers());
			model.addAttribute("roles",rolesRepository.findAll());
			model.addAttribute("formTab","active");
			
		}
		else {
			try {			
				usuarioService.createUser(user);
				model.addAttribute("usuarioFormulario", new Usuario());
				model.addAttribute("listTab","active");
				model.addAttribute("successMessage","Usuario creado correctamente");
				model.addAttribute("usuarioLista",usuarioService.getAllUsers());
				model.addAttribute("roles",rolesRepository.findAll());
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("usuarioFormulario", user);
				model.addAttribute("formTab","active");
				model.addAttribute("usuarioLista",usuarioService.getAllUsers());
				model.addAttribute("roles",rolesRepository.findAll());
			}
			
		}
		
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
		
		try {
			Usuario userToEdit =usuarioService.getUserById(id);
			model.addAttribute("usuarioFormulario", userToEdit);
			model.addAttribute("usuarioLista", usuarioService.getAllUsers());
			model.addAttribute("roles",rolesRepository.findAll());
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
			model.addAttribute("passwordForm",new ChangePasswordForm(userToEdit.getId()));
		}
		catch (Exception e) {
			model.addAttribute("listErrorMessage",e.getMessage());
			model.addAttribute("usuarioFormulario", new Usuario());
			model.addAttribute("listTab","active");
			model.addAttribute("usuarioLista",usuarioService.getAllUsers());
			model.addAttribute("roles",rolesRepository.findAll());
			/*return "redirect:/userForm";*/
		}
		
		

		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("usuarioFormulario")Usuario user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("usuarioFormulario",user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
			model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
		}else {
			try {
				usuarioService.updateUser(user);
				model.addAttribute("usuarioFormulario", new Usuario());
				model.addAttribute("successMessage","Usuario actualizado correctamente");
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("usuarioFormulariom", user);
				model.addAttribute("formTab","active");
				model.addAttribute("usuarioLista", usuarioService.getAllUsers());
				model.addAttribute("roles",rolesRepository.findAll());
				model.addAttribute("editMode","true");
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			}
		}

		model.addAttribute("usuarioLista", usuarioService.getAllUsers());
		model.addAttribute("roles",rolesRepository.findAll());
		return "user-form/user-view";

	}
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id") Long id) {
		try {
			usuarioService.deleteUser(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","El usuario no pudo ser eliminado.");
		}
		/*return userForm(model);*/
		return "redirect:/userForm";
	}
	
	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
		try {
			//If error, just return a 400 bad request, along with the error message
	        if (errors.hasErrors()) {
	            String result = errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining(""));

	            throw new Exception(result);
	        }
			usuarioService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("success");
	}
}
