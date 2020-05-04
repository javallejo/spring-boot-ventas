package com.cuasatar.ventas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.exception.UsernameOrIdNotFound;
import com.cuasatar.ventas.repository.EstadosRepository;
import com.cuasatar.ventas.service.ClienteService;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteController {
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	ClienteService clienteService;
	
	
	@GetMapping("/clientForm")
	public String clientForm(Model model){	
		
		model.addAttribute("clienteFormulario", new Cliente());
		model.addAttribute("estado",estadosRepository.findAll());
		return "ventas/client-view";
		
	}
	
	@PostMapping("/clientForm")
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
				model.addAttribute("estado",estadosRepository.findAll());
				
			}
			catch (Exception e) {
				model.addAttribute("formErrorClientMessage",e.getMessage());
				model.addAttribute("clienteFormulario",client);
				model.addAttribute("estado",estadosRepository.findAll());
			}
		}
		return "ventas/client-view";
	}
	
	
	
	
	@GetMapping("/clientList")
	public String clientList(Model model){	
		
		model.addAttribute("clienteLista",clienteService.getAllClients());
		return "ventas/client-list-view";
		
	}
	/*
	@GetMapping("/ventas")
	
	public String ventas(Model model)  {
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "ventas/ventas";
	}
	*/
	
	
	@GetMapping("/editClient/{id}")
	public String getEditClientForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
		
		try {
			Cliente clientToEdit =clienteService.getClientById(id);
			model.addAttribute("clienteFormulario", clientToEdit);
			model.addAttribute("estado",estadosRepository.findAll());
			model.addAttribute("editClientMode","true");
		}
		catch (Exception e) {
			model.addAttribute("clienteFormulario", new Cliente());
			model.addAttribute("formErrorClientMessage",e.getMessage());
			model.addAttribute("editClientMode","true");	
		}
		
		return  "ventas/client-view";
	}
	
	@PostMapping("/editClient")
	public String postEditClientForm(@Valid @ModelAttribute("clienteFormulario")Cliente client, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("clienteFormulario", client);
			model.addAttribute("editClientMode","true");
		}
		else {
			try {
				clienteService.updateClient(client);
				model.addAttribute("clienteFormulario", new Cliente());
				model.addAttribute("successClientMessage","Cliente actualizado correctamente");
			}
			catch (Exception e) {
				model.addAttribute("clienteFormulario", client);
				model.addAttribute("formErrorClientMessage",e.getMessage());
				model.addAttribute("estado",estadosRepository.findAll());
				model.addAttribute("editClientMode","true");
				
			}
		}
		
		
		return "redirect:/clientes/clientList";
	}
	
	
	@GetMapping("/deleteClient/{id}")
	public String deleteUser(Model model, @PathVariable(name="id") Long id) {
		try {
			clienteService.deleteClient(id);
		} catch (Exception e ) {
			model.addAttribute("deleteClientError",e.getMessage());
		}
		/*return clientList(model);*/
		return "redirect:/clientes/clientList";
	}
	
	
	@GetMapping("/clientForm/cancel")
	public String cancelClient(ModelMap model) {
		return "redirect:/clientes/clientForm";
	}
	
	
	

}
