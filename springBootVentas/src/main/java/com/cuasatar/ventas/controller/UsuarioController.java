package com.cuasatar.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.RolesRepository;
import com.cuasatar.ventas.service.UsuarioService;


@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolesRepository rolesRepository;
	
	
	@GetMapping("/")
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
}
