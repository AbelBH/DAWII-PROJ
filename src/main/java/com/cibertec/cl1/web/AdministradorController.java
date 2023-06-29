package com.cibertec.cl1.web;

import com.cibertec.cl1.domain.Orden;
import com.cibertec.cl1.domain.Producto;
import com.cibertec.cl1.domain.Usuario;
import com.cibertec.cl1.servicio.OrdenService;
import com.cibertec.cl1.servicio.ProductoService;
import com.cibertec.cl1.servicio.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ABEL
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private OrdenService ordenService;
    
    //links del servicio:
    String URLlistadoProductos      ="http://localhost:8083/Producto/list/";
    String URLlistadousuario        ="http://localhost:8083/Usuario/list/";
    String urllistadoOrden          ="http://localhost:8083/Orden/list/";
    String urlListadoOrdenDetalle   ="http://localhost:8083/Orden/Detalle/list/";
    
    
    //RestTemplate
    RestTemplate restTemplate = new RestTemplate();
    
    @GetMapping("")
    public String home(Model model) {
      //  List<Producto> productos = productoService.listarproductos();
        ResponseEntity<Producto[]> response = restTemplate.exchange(URLlistadoProductos, HttpMethod.GET, null, Producto[].class);
        Producto[] productos = response.getBody();
        
        model.addAttribute("productos", productos);
        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        ResponseEntity<Usuario[]> response = restTemplate.exchange(URLlistadousuario, HttpMethod.GET, null, Usuario[].class);
        Usuario[] usuarios = response.getBody();
        
//        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("usuarios", usuarios);
        return "administrador/usuarios";
    }
    
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        ResponseEntity<Orden[]> response = restTemplate.exchange(urllistadoOrden, HttpMethod.GET, null, Orden[].class);
        Orden[] ordenes = response.getBody();
//        model.addAttribute("ordenes", ordenService.findAll());
model.addAttribute("ordenes", ordenes);
        return "administrador/ordenes";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        Orden orden = ordenService.findById(id).get();
        
        model.addAttribute("detalles", orden);
        return "administrador/detalleorden";
    }
}
