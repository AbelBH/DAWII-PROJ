package com.cibertec.cl1.web;

import com.cibertec.cl1.domain.Producto;
import com.cibertec.cl1.domain.Usuario;
import com.cibertec.cl1.servicio.ImagenService;
import com.cibertec.cl1.servicio.ProductoService;
import com.cibertec.cl1.servicio.UsuarioService;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ABEL
 */
@Controller
@Slf4j
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ImagenService imagenService;
    
    @GetMapping("")
    public String show(Model model) {
        var productos = productoService.listarproductos();
        model.addAttribute("productos",productos);
        return "productos/show";
    }
    
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto,@RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        Usuario u = usuarioService.findbyId(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        
        //imagen
        if (producto.getId()==null) {
            String nombreImagen = imagenService.saveImage(file);
            producto.setImagen(nombreImagen);
        }else{
            
        }
        
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(Producto producto, Model model) {
        producto = productoService.encontrarProducto(producto);
        model.addAttribute("producto",producto);
        return "productos/edit";
    }
    
    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            Producto p = new Producto();
            p=productoService.get(producto.getId()).get();
            producto.setImagen(p.getImagen());
        }else{
            
            Producto p = new Producto();
            p=productoService.get(producto.getId()).get();
            if (!p.getImagen().equals("default.jpg")) {
                imagenService.deleteImage(p.getImagen());
            }
            
            String nombreImagen = imagenService.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(Producto producto) {
        Producto p = new Producto();
        p=productoService.get(producto.getId()).get();
        if (!p.getImagen().equals("default.jpg")) {
            imagenService.deleteImage(p.getImagen());
        }
        
        productoService.eliminarProducto(producto);
        return "redirect:/productos";
    }
}
