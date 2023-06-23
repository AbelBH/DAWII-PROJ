package com.cibertec.cl1.web;

import com.cibertec.cl1.domain.DetalleOrden;
import com.cibertec.cl1.domain.Orden;
import com.cibertec.cl1.domain.Producto;
import com.cibertec.cl1.domain.Usuario;
import com.cibertec.cl1.servicio.DetalleOrdenService;
import com.cibertec.cl1.servicio.OrdenService;
import com.cibertec.cl1.servicio.ProductoService;
import com.cibertec.cl1.servicio.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ABEL
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private OrdenService ordenService;
    
    @Autowired
    private DetalleOrdenService detalleordenService;
    
    //Almacenar detalles
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
    
    //Datos de la orden
    Orden orden = new Orden();
    
    @GetMapping("")
    public String home(Model model, HttpSession session) {
        
        model.addAttribute("productos", productoService.listarproductos());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/home";
    }
    
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
    
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        
        Optional<Producto> optionalProducto = productoService.get(id);
        producto=optionalProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);
        
        //No se añada 2 veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p->p.getProducto().getId()==idProducto);
        if (!ingresado) {
            detalles.add(detalleOrden);
        }
        
        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        return "usuario/carrito";
    }
    
    //Quitar producto
    @GetMapping("/delete/cart/{id}")
    public String deleteCart(@PathVariable Integer id, Model model) {
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
        for(DetalleOrden detalleOrden: detalles) {
            if (detalleOrden.getProducto().getId()!=id) {
                ordenesNueva.add(detalleOrden);
            }
        }
        //Lista con productos restantes
        detalles = ordenesNueva;
        double sumaTotal = 0;
        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }
    
    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "/usuario/carrito";
    }
    
    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        Usuario usuario = usuarioService.findbyId(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        return "usuario/resumenorden";
    }
    
    //Guardar orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());
        
        //Usuario
        Usuario usuario = usuarioService.findbyId(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);
        
        //Guardar detalles
        for (DetalleOrden dt : detalles) {
            dt.setOrden(orden);
            detalleordenService.save(dt);
        }
        
        //Limpiar lista
        orden = new Orden();
        detalles.clear();
        return "redirect:/";
    }
    
    @PostMapping("/search")
    public String search(@RequestParam String nombre, Model model) {
        List<Producto> productos = productoService.listarproductos().stream().filter(p->p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";
    }
}
