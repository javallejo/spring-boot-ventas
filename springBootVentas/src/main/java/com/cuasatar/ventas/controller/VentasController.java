package com.cuasatar.ventas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cuasatar.ventas.dto.ClienteParaVender;

import com.cuasatar.ventas.dto.ProductoDisponibleDTO;
import com.cuasatar.ventas.dto.ProductoParaVenderDTO;

import com.cuasatar.ventas.dto.VentasDTO;
import com.cuasatar.ventas.entity.Cliente;
import com.cuasatar.ventas.entity.DetalleVentas;
import com.cuasatar.ventas.entity.Producto;
import com.cuasatar.ventas.entity.Usuario;
import com.cuasatar.ventas.entity.Ventas;
import com.cuasatar.ventas.repository.ClientesRepository;
import com.cuasatar.ventas.repository.DetalleVentasRepository;
import com.cuasatar.ventas.repository.ProductoRepository;
import com.cuasatar.ventas.repository.VentasRepository;
import com.cuasatar.ventas.service.ClienteService;
import com.cuasatar.ventas.service.DetalleVentasService;
import com.cuasatar.ventas.service.ProductoService;
import com.cuasatar.ventas.service.UsuarioService;
import com.cuasatar.ventas.service.VentasService;
import com.cuasatar.ventas.util.GenerarSerie;


@Controller
@RequestMapping(path = "/ventas")
public class VentasController {
	
	/*Obtener lista cuando itera solamente el supervisor*/
	List<ClienteParaVender> listaClientesActivos=null;
	
	Iterable<Long> idClienteActiveIt=null;
	List<Long> idClientActive = new ArrayList<>();
	
	
	
	List<ProductoDisponibleDTO> listaProductosActivos=null;
	
	Iterable<Long> idProductoActiveIt=null;
	List<Long> idProductoActive = new ArrayList<>();
	
	
	VentasDTO vdto= new VentasDTO();
	int item;
	int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    
    /*items de ventas*/
    Usuario miUsuario=null;
    Long id_cliente=(long) 0;
    Cliente miCliente=null;
    String numSerie="";
    Date fecha=new Date();
    int nprovendidos=0;
   /* Set<EstadoVenta> estadoventa=null;
    EstadoVenta eventa=null;*/
	
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClientesRepository clienteRepository;
	
	@Autowired 
	ProductoService productoService;
	
	@Autowired 
	ProductoRepository productoRepository;
	
	@Autowired
	VentasService ventasService;
	@Autowired
	UsuarioService usuarioService;
	

	
	@Autowired
	DetalleVentasRepository detalleventasRepository;
	
	@Autowired
	DetalleVentasService detalleventasService;
	

	

	

	
	String numeroserieActual="";
	
	List<VentasDTO> listaProductos = new ArrayList<>();
	
	@PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVenderDTO> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/ventas/";
    }
	
	private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }
	
	private void limpiarClienteCarrito(HttpServletRequest request) {
        this.guardarClienteCarrito(new ArrayList<>(), request);
    }
	
	 @GetMapping(value = "/limpiar")
	    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
	        this.limpiarCarrito(request);
	        this.limpiarClienteCarrito(request);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Venta cancelada")
	                .addFlashAttribute("clase", "info");
	        return "redirect:/ventas/";
	    }
	 
	 public void ObtenerClientesActivos() throws Exception {
		 listaClientesActivos=clienteService.fetchClientesEstadoActiveList();
		 for (ClienteParaVender cpv:listaClientesActivos) {
			 idClientActive.add(cpv.getId());
			}	 	 	 
		 idClienteActiveIt=(Iterable<Long>) idClientActive;	
	 }
	
	
	@GetMapping(value = "/")
    public String interfaceVentas(Model model, HttpServletRequest request) throws Exception {
		
		//ObtenerClientesActivos();
		numeroserieActual=obtenerNumeroSerieActual(numeroserieActual,ventasService);
		model.addAttribute("numeroserie", numeroserieActual);
        model.addAttribute("producto", new Producto());
        model.addAttribute("cliente", new ClienteParaVender());
        //model.addAttribute("clienteList", clienteService.getClientListById(idClienteActiveIt));
        Double total =  0.0;
        ArrayList<ProductoParaVenderDTO> carrito = this.obtenerCarrito(request);
        ArrayList<Cliente> clientecarrito = this.obtenerClienteCarrito(request);
        for (ProductoParaVenderDTO p: carrito) total += p.getTotal();
        model.addAttribute("total", total);
        return "ventas/sales-view";
    }
	
	private ArrayList<ProductoParaVenderDTO> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductoParaVenderDTO> carrito = (ArrayList<ProductoParaVenderDTO>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }
	
	private ArrayList<Cliente> obtenerClienteCarrito(HttpServletRequest request) {
        ArrayList<Cliente> clientecarrito = (ArrayList<Cliente>) request.getSession().getAttribute("clientecarrito");
        if (clientecarrito == null) {
        	clientecarrito = new ArrayList<>();
        }
        return clientecarrito;

    }
	
	@PostMapping(value = "/cliente/agregar")
	public String agregarClienteAlCarrito(@Valid Cliente cliente, HttpServletRequest request, RedirectAttributes redirectAttrs) throws Exception {
		ArrayList<Cliente> clientecarrito = this.obtenerClienteCarrito(request);
		System.out.println("mi lista cliente->"+cliente.getDni());
		
		Cliente clienteBuscadoPorDni = clienteService.getClientByDni(cliente.getDni());
		if (clienteBuscadoPorDni == null) {
			redirectAttrs
            .addFlashAttribute("mensaje", "El cliente con dni " + cliente.getDni() + " no existe")
            .addFlashAttribute("clase", "warning");
			return "redirect:/ventas/";
		}
		boolean encontrado = false;
		for (Cliente clienteBuscadoPorDniActual : clientecarrito) {
         	if (clienteBuscadoPorDniActual.getId().equals(clienteBuscadoPorDni.getId())) {
				System.out.println("encontrado");
                encontrado = true;
                break;
            }
        }
		if (!encontrado) {
			System.out.println("no encontrado");
			clientecarrito.add(new Cliente(clienteBuscadoPorDni.getId(),clienteBuscadoPorDni.getDni(), clienteBuscadoPorDni.getNombres(), clienteBuscadoPorDni.getDireccion()));
			/*model.addAttribute("clienteNombre", clienteBuscadoPorDni.getNombres());
			model.addAttribute("estadocliente","true");*/
			redirectAttrs
            .addFlashAttribute("mensaje", "El cliente con nombre " + clienteBuscadoPorDni.getNombres() + " esta asignado para esta venta")
            .addFlashAttribute("clase", "info");
		}
		this.guardarClienteCarrito(clientecarrito, request);				

		return "redirect:/ventas/";
	}
	
	private void guardarClienteCarrito(ArrayList<Cliente> clientecarrito, HttpServletRequest request) {
        request.getSession().setAttribute("clientecarrito", clientecarrito);
    }
	
	
	@PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) throws Exception {
        ArrayList<ProductoParaVenderDTO> carrito = this.obtenerCarrito(request);
        Producto productoBuscadoPorCodigo = productoService.getProductById(producto.getId());
        

        if (productoBuscadoPorCodigo == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " + producto.getId() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/ventas/";
        }
        
        if (productoBuscadoPorCodigo.sinExistencia()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/ventas/";
        }
        boolean encontrado = false;
        for (ProductoParaVenderDTO productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getId().equals(productoBuscadoPorCodigo.getId())) {
            	
            	if(!CompararStockConCompra(productoBuscadoPorCodigo.getCantidad(), productoParaVenderActual.getCantidadProducto())) {
            		productoParaVenderActual.aumentarCantidadProducto();
                    encontrado = true;
                    break;
            	}
            	else {
            		redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " + producto.getId() + " no cuenta con más existencias en almacén")
                    .addFlashAttribute("clase", "warning");
            		return "redirect:/ventas/";
            	}
                
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVenderDTO(productoBuscadoPorCodigo.getId(),productoBuscadoPorCodigo.getNombre(), productoBuscadoPorCodigo.getPrecio(), productoBuscadoPorCodigo.getCantidad(), (double) 1));
        }
        
        this.guardarCarrito(carrito, request);
        return "redirect:/ventas/";
	 }
	
	private void guardarCarrito(ArrayList<ProductoParaVenderDTO> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }
	
	
	private String obtenerNumeroSerieActual(String numeroserieActual,VentasService ventasService) throws Exception {
		
		
		VentasDTO vdto=new VentasDTO();
		vdto=ventasService.fetchNumeroSerieVentas();
		numeroserieActual=vdto.getNumeroserie();
		
		/*System.out.println("mi numero serie actual->"+numeroserieActual);*/
		if (numeroserieActual == null || numeroserieActual.isEmpty() || numeroserieActual.length()==0 ) {
			numeroserieActual = "000000001";     
        } else {
            int incrementar = Integer.parseInt(numeroserieActual);
            GenerarSerie gs = new GenerarSerie();
            numeroserieActual = gs.NumeroSerie(incrementar);         
        }	
		
		return numeroserieActual;
	}
	
	@PostMapping(value = "/terminar")
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) throws Exception {
        ArrayList<ProductoParaVenderDTO> carrito = this.obtenerCarrito(request);/*Carrito productos*/
        ArrayList<Cliente> cliente=this.obtenerClienteCarrito(request);/*Obtener cliente*/
        // Si no hay carrito o está vacío, regresamos inmediatamente
        if (carrito == null || carrito.size() <= 0) {
        	redirectAttrs
            .addFlashAttribute("mensaje", "Introduzca objetos al carrito de ventas")
            .addFlashAttribute("clase", "warning");
            return "redirect:/ventas/";
        }
        
        if (cliente == null || cliente.size() <= 0) {
        	redirectAttrs
            .addFlashAttribute("mensaje", "Introduzca un cliente a la venta")
            .addFlashAttribute("clase", "warning");
            return "redirect:/ventas/";
        }
        
        for (Cliente clienteParaVender : cliente) {
        	id_cliente=clienteParaVender.getId();
        }
        
        miCliente=clienteService.getClientById(id_cliente);
        
        Double total =  0.0;
        for (ProductoParaVenderDTO p: carrito) total += p.getTotal();
        
        miUsuario=usuarioService.getUserLog();
        numeroserieActual=obtenerNumeroSerieActual(numeroserieActual,ventasService);
        
        /*
        eventa=estadoventasService.getEstadoVentasById((long) 1);
        estadoventa.add(eventa);
        */

        
        Ventas v = ventasService.createSales(new Ventas(miCliente,miUsuario,numeroserieActual,fecha,total));
        
        /**/
        
        
        // Recorrer el carrito
        for (ProductoParaVenderDTO productoParaVender : carrito) {
            // Obtener el producto fresco desde la base de datos
            Producto p = productoRepository.findById(productoParaVender.getId()).orElse(null);
            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            // Le restamos existencia
            nprovendidos=(int) (productoParaVender.getTotal()/productoParaVender.getPrecio());

            p.setCantidad(NuevoStockProducto(p,nprovendidos));
            // Lo guardamos con la existencia ya restada
            productoRepository.save(p);
            
            
            
            
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            DetalleVentas detalleVentas= new DetalleVentas(v, p, productoParaVender.getCantidadProducto().intValue(),productoParaVender.getTotal());
            // Y lo guardamos
            detalleventasRepository.save(detalleVentas);
            
        }             
       
        // Al final limpiamos el carrito
        this.limpiarCarrito(request);
        this.limpiarClienteCarrito(request);
        // e indicamos una venta exitosa
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/ventas/";
        
        
       
    }
	
	public int NuevoStockProducto(Producto p,int nproductosvendidos) {
		int nuevoStock=p.getCantidad()-nproductosvendidos;			
		return nuevoStock;
	}
	
	public boolean CompararStockConCompra(int cantidadStock, Double cantidadCompra) {
		boolean iguales=false;
		int cantidadCompraInt=(int) Math.round(cantidadCompra);
		if (cantidadStock-cantidadCompraInt==0) {
			iguales= true;
		}
		else {
			iguales=false;
		}
		return iguales;
	}
	
	@GetMapping(value = "/reporte-ventas")
    public String reporteVentas(Model model) throws Exception  {
		model.addAttribute("ventas", ventasService.getAllSales());
		
		
		
		
		/*System.out.println("mis ventas->"+ventasService.getAllSales().toString());*/
		/*model.addAttribute("ventasDetalle", detalleventasService.getAllSalesDetails());
		System.out.println("mis ventas detalle->"+detalleventasService.getAllSalesDetails().toString());*/
		
		return "ventas/report-sales-view";
		
		
	}
	

	
	@RequestMapping(value = "/detalle-venta-cliente")	
	@ResponseBody
    public Ventas ventaPorCliente(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

    	   
    	/*   
    	model.addAttribute("ventasUnico", ventasService.getSalesById(id));
   		model.addAttribute("ventasDetalle",detalleventasService.getDetailSalesById(id));
   		model.addAttribute("modalSaleDetailMode","false");
   		
   		*/
   		
   		/*System.out.println("mis ventas->"+ventasService.getSalesById(id).toString());*/
   		/*System.out.println("mis ventas detalle->"+detalleventasService.getDetailSalesById(id).toString());*/
   		
		
		
		return ventasService.getSalesById(id);
      
   		/*return "la respuesta es hola";*/
       
      
       
		
		
		
		/*return model;*/
		
    }
	
	@RequestMapping(value = "/detalle-venta-descripcion")
	@ResponseBody
	 public Iterable<DetalleVentas> ventaPorClienteDescripcion(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
       Ventas venta=ventasService.getSalesById(id);
      
       
       System.out.println("mis ventas detalle->"+detalleventasService.getDetailBySales(venta).toString());
      	
       
       return detalleventasService.getDetailBySales(venta);
      
	}

	
	
}
