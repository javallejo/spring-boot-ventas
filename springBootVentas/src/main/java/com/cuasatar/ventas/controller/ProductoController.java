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

import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.repository.DisponibleRepository;
import com.cuasatar.ventas.service.ProductoService;

@Controller
@RequestMapping(path = "/producto")
public class ProductoController {
	
	
	@Autowired
	DisponibleRepository disponibleRepository;
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/productForm")
	public String productForm(Model model){	
		
		model.addAttribute("productoFormulario", new Producto());
		model.addAttribute("disponible",disponibleRepository.findAll());
		return "ventas/product-view";
		
	}
	
	@PostMapping("/productForm")
	public String createClient(@Valid @ModelAttribute("productoFormulario")Producto product, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("productoFormulario",product);
			model.addAttribute("disponible",disponibleRepository.findAll());
			
		}
		else {
			try {	
				productoService.createProduct(product);
				model.addAttribute("productoFormulario", new Producto());
				model.addAttribute("successProductMessage","Producto creado correctamente");
				model.addAttribute("disponible",disponibleRepository.findAll());
				
			}
			catch (Exception e) {
				model.addAttribute("formErrorProductMessage",e.getMessage());
				model.addAttribute("productoFormulario",product);
				model.addAttribute("disponible",disponibleRepository.findAll());
			}
		}
		return "ventas/product-view";
	}
	
	@GetMapping("/productList")
	public String productList(Model model){	
		
		model.addAttribute("productoLista",productoService.getAllProducts());
		return "ventas/product-list-view";
		
	}
	
	@GetMapping("/editProduct/{id}")
	public String getEditClientForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
		
		try {
			Producto productToEdit =productoService.getProductById(id);
			model.addAttribute("productoFormulario", productToEdit);
			model.addAttribute("disponible",disponibleRepository.findAll());
			model.addAttribute("editProductMode","true");
		}
		catch (Exception e) {
			model.addAttribute("productoFormulario", new Producto());
			model.addAttribute("formErrorProductMessage",e.getMessage());
			model.addAttribute("editProductMode","true");	
		}
		
		return  "ventas/product-view";
	}
	
	@PostMapping("/editProduct")
	public String postEditClientForm(@Valid @ModelAttribute("productoFormulario")Producto product, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("productoFormulario", product);
			model.addAttribute("editProductMode","true");
		}
		else {
			try {
				productoService.updateProduct(product);
				model.addAttribute("clienteFormulario", new Producto());
				model.addAttribute("successProductMessage","Producto actualizado correctamente");
			}
			catch (Exception e) {
				model.addAttribute("productoFormulario", product);
				model.addAttribute("formErrorProductMessage",e.getMessage());
				model.addAttribute("disponible",disponibleRepository.findAll());
				model.addAttribute("editProductMode","true");
				
			}
		}
		
		
		return "redirect:/producto/productList";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteproduct(Model model, @PathVariable(name="id") Long id) {
		try {
			productoService.deleteProduct(id);
		} catch (Exception e ) {
			model.addAttribute("deleteProductError",e.getMessage());
		}
		/*return clientList(model);*/
		return "redirect:/producto/productList";
	}
	
	
	@GetMapping("/productForm/cancel")
	public String cancelProduct(ModelMap model) {
		return "redirect:/producto/productForm";
	}
	
}
