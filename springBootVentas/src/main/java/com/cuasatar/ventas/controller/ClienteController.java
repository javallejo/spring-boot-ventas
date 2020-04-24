package com.cuasatar.ventas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.repository.EstadosRepository;
import com.cuasatar.ventas.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	ClienteService clienteService;
	
	
	@GetMapping("/ventas/clientForm")
	public String clientForm(Model model){	
		
		model.addAttribute("clienteFormulario", new Cliente());
		model.addAttribute("estado",estadosRepository.findAll());
		return "ventas/client-view";
		
	}
	
	@PostMapping("/ventas/clientForm")
	public String createClient(@Valid @ModelAttribute("clienteFormulario")Cliente client, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("clienteFormulario",client);
			model.addAttribute("estado",estadosRepository.findAll());
			
		}
		else {
			try {	
				clienteService.createClient(client);
				model.addAttribute("clienteFormulario", new Cliente());
				model.addAttribute("successClientMessage","Cliente creado correctamente");
				
			}
			catch (Exception e) {
				model.addAttribute("formErrorClientMessage",e.getMessage());
				model.addAttribute("clienteFormulario",client);
				model.addAttribute("estado",estadosRepository.findAll());
			}
		}
		return "ventas/client-view";
	}
	
	
	
	
	@GetMapping("/ventas/clientList")
	public String clientList(Model model){	
		
		model.addAttribute("clienteLista",clienteService.getAllClients());
		return "ventas/client-list-view";
		
	}
	
	@GetMapping("/ventas")
	
	public String ventas(Model model)  {
		try {
		/*	getUserDetails();
			model.addAttribute("usuario",miUsuarioDatos);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "ventas/ventas";
	}
	
	@GetMapping("/ventas/clientForm/cancel")
	public String cancelClient(ModelMap model) {
		return "redirect:/ventas/clientForm";
	}
	

}
