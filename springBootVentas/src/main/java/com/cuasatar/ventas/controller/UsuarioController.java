package com.cuasatar.ventas.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.cuasatar.ventas.IAuthenticationFacade;
import com.cuasatar.ventas.dto.ChangePasswordForm;
import com.cuasatar.ventas.dto.UsuarioRolesDTO;
import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.entity.Roles;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.exception.UsernameOrIdNotFound;
import com.cuasatar.ventas.repository.EstadosRepository;
import com.cuasatar.ventas.repository.RolesRepository;
import com.cuasatar.ventas.service.RoleService;
import com.cuasatar.ventas.service.UsuarioService;


@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	
	@Autowired
	RoleService rolesService;
	
	@Autowired
	RolesRepository rolesRepository;
	
	
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	Usuario miUsuarioComprobacion = null;
	Set<Roles> setRoles = null;
	
	
	/*Obtener lista cuando itera solamente el usuario*/
	List list = null;
	Roles myRol=null;
	String tipoRol="";
	Long idUsuario=null;
	List<Long> idUser = new ArrayList<>();
	Iterable<Long> idUserIt=null;
	

	
	
	/*Obtener lista cuando itera solamente el supervisor*/
	List<UsuarioRolesDTO> listaUsuariosSupervisor=null;
	List<Long> idUserSupervisor = new ArrayList<>();
	Iterable<Long> idUserSupervisorIt=null;
	
	
	
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}

	@GetMapping("/userForm")
	public String userForm(Model model){	
		
		try {
			getIdAndRoleUserLogged();
			
			/**/

			model.addAttribute("usuarioFormulario", new Usuario());
			if(tipoRol.equals("ROLE_USER")) {
				model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
				model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
			}
			else if(tipoRol.equals("ROLE_SUPERVISOR")) {
				model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
				model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
			}
			else {
				model.addAttribute("usuarioLista",usuarioService.getAllUsers());
				model.addAttribute("roles",rolesRepository.findAll());
			}
			 
			
            /*model.addAttribute("roles",rolesRepository.findAll());*/
			model.addAttribute("listTab","active");								
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return "user-form/user-view";
	}
	
	

	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("usuarioFormulario")Usuario user, BindingResult result, ModelMap model) {
		String nuevaContrasena="";
		String nuevaContrasenaConfirm="";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		
		try {
			
			getIdAndRoleUserLogged();							
			
			if(result.hasErrors()) {			
				model.addAttribute("usuarioFormulario", user);
				if(tipoRol.equals("ROLE_USER")) {
					model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
					model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
				}
				else if(tipoRol.equals("ROLE_SUPERVISOR")) {
					model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
					model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
				}
				else {
					model.addAttribute("usuarioLista",usuarioService.getAllUsers());
					model.addAttribute("roles",rolesRepository.findAll());
				}
				 
				
	            /*model.addAttribute("roles",rolesRepository.findAll());*/
				model.addAttribute("formTab","active");
				
			}
			else {
				try {			
					usuarioService.createUser(user);
					model.addAttribute("usuarioFormulario", new Usuario());
					model.addAttribute("listTab","active");
					model.addAttribute("successMessage","Usuario creado correctamente");
					if(tipoRol.equals("ROLE_USER")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else if(tipoRol.equals("ROLE_SUPERVISOR")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else {
						model.addAttribute("usuarioLista",usuarioService.getAllUsers());
						model.addAttribute("roles",rolesRepository.findAll());
					}
					 
					
		            /*model.addAttribute("roles",rolesRepository.findAll());*/
					
				} catch (Exception e) {
					model.addAttribute("formErrorMessage",e.getMessage());
					model.addAttribute("usuarioFormulario", user);
					model.addAttribute("formTab","active");
					if(tipoRol.equals("ROLE_USER")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else if(tipoRol.equals("ROLE_SUPERVISOR")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else {
						model.addAttribute("usuarioLista",usuarioService.getAllUsers());
						model.addAttribute("roles",rolesRepository.findAll());
					}
					 
					
		            /*model.addAttribute("roles",rolesRepository.findAll());*/
				}
				
			}
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
		
		
		try {
			getIdAndRoleUserLogged();
			
			if(tipoRol.equals("ROLE_USER") && idUsuario!=id ) {/*Esta tratando de editar un usuario que no le corresponde*/
				return "redirect:/userForm";
			}
			else {
				try {
					Usuario userToEdit =usuarioService.getUserById(id);
					model.addAttribute("usuarioFormulario", userToEdit);
					if(tipoRol.equals("ROLE_USER")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else if(tipoRol.equals("ROLE_SUPERVISOR")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else {
						model.addAttribute("usuarioLista",usuarioService.getAllUsers());
						model.addAttribute("roles",rolesRepository.findAll());
					}
					 
					
		            /*model.addAttribute("roles",rolesRepository.findAll());*/
					model.addAttribute("formTab","active");
					model.addAttribute("editMode","true");
					model.addAttribute("passwordForm",new ChangePasswordForm(userToEdit.getId()));
				}
				catch (Exception e) {
					model.addAttribute("listErrorMessage",e.getMessage());
					model.addAttribute("usuarioFormulario", new Usuario());
					model.addAttribute("listTab","active");
					if(tipoRol.equals("ROLE_USER")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else if(tipoRol.equals("ROLE_SUPERVISOR")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else {
						model.addAttribute("usuarioLista",usuarioService.getAllUsers());
						model.addAttribute("roles",rolesRepository.findAll());
					}
					 
					
		            /*model.addAttribute("roles",rolesRepository.findAll());*/
					/*return "redirect:/userForm";*/
				}
			}
			
			
			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
							
		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("usuarioFormulario")Usuario user, BindingResult result, ModelMap model) {
		
		try {
			getIdAndRoleUserLogged();
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
					if(tipoRol.equals("ROLE_USER")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else if(tipoRol.equals("ROLE_SUPERVISOR")) {
						model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
						model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
					}
					else {
						model.addAttribute("usuarioLista",usuarioService.getAllUsers());
						model.addAttribute("roles",rolesRepository.findAll());
					}
					 
					
		            /*model.addAttribute("roles",rolesRepository.findAll());*/
					model.addAttribute("editMode","true");
					model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
				}
			}

			if(tipoRol.equals("ROLE_USER")) {
				model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserIt));
				model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
			}
			else if(tipoRol.equals("ROLE_SUPERVISOR")) {
				model.addAttribute("usuarioLista",usuarioService.getUserListById(idUserSupervisorIt));
				model.addAttribute("roles",rolesRepository.findByDescripcion("ROLE_USER"));
			}
			else {
				model.addAttribute("usuarioLista",usuarioService.getAllUsers());
				model.addAttribute("roles",rolesRepository.findAll());
			}
			 
			
            /*model.addAttribute("roles",rolesRepository.findAll());*/
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
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
		} catch (UsernameOrIdNotFound uoin) {
			model.addAttribute("deleteError",uoin.getMessage());
		}
		return userForm(model);
		/*return "redirect:/userForm";*/
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
	
	public void getIdAndRoleUserLogged() throws Exception {
		
		miUsuarioComprobacion = authenticationFacade.getLoggedUsuario();
		setRoles = miUsuarioComprobacion.getRoles();
		
		list = Arrays.asList(setRoles.toArray());
		myRol=(Roles) list.get(0);
		tipoRol=myRol.getDescripcion();	
		
		
		idUsuario=(Long)miUsuarioComprobacion.getId();
		
		
		if(tipoRol.equals("ROLE_USER")) {
			idUser.add(idUsuario);
			idUserIt=(Iterable<Long>) idUser;
		}
		
		if(tipoRol.equals("ROLE_SUPERVISOR")) {
			listaUsuariosSupervisor=usuarioService.fetchUsuarioRolesUserInnerJoin();
			
			for (UsuarioRolesDTO ur:listaUsuariosSupervisor) {
				idUserSupervisor.add(ur.getId());
			}
		idUserSupervisorIt=(Iterable<Long>) idUserSupervisor;	
		}
		
		

			
		}
		//idUserSupervisor
		
		//idUserSupervisorIt
	/*
	public void getUserDetails() throws Exception {
		miUsuarioDatos=authenticationFacade.getLoggedUsuario();
	}*/
		
		
		

		
	
	
	
}
