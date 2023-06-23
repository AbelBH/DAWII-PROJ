/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.cl1.servicio;

import com.cibertec.cl1.domain.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ABEL
 */
public interface ProductoService {
    public List<Producto> listarproductos(); 
    public void guardarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
    public Producto encontrarProducto(Producto producto);
    public Optional<Producto> get(Integer id);
}
