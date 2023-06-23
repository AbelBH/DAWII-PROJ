package com.cibertec.cl1.web;

import com.cibertec.cl1.domain.Orden;
import com.cibertec.cl1.domain.Producto;
import com.cibertec.cl1.servicio.OrdenService;
import com.cibertec.cl1.servicio.ProductoService;
import com.cibertec.cl1.servicio.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.listarproductos();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }
    
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        Orden orden = ordenService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalleOrden());
        return "administrador/detalleorden";
    }
}
