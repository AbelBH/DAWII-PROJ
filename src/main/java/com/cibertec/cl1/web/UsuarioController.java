package com.cibertec.cl1.web;

import com.cibertec.cl1.domain.Orden;
import com.cibertec.cl1.domain.Usuario;
import com.cibertec.cl1.servicio.OrdenService;
import com.cibertec.cl1.servicio.UsuarioService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ABEL
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private OrdenService ordenService;
    
    BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
    
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario) {
        usuario.setTipo("USER");
        usuario.setPassword(PasswordEncoder.encode(usuario.getPassword()));
        usuarioService.guardarUsuario(usuario);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }
    
    @GetMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        Optional<Usuario> user = usuarioService.findbyId(Integer.parseInt(session.getAttribute("idusuario").toString()));
        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            }else {
                return "redirect:/";
            }
        }else {
            System.out.println("Usuario no existe");
        }
        return "redirect:/";
    }
    
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario = usuarioService.findbyId(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        model.addAttribute("ordenes",ordenes);
        return "usuario/compras";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalleOrden());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }
    
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
