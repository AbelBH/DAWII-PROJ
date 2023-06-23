/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.cl1.servicio;

import com.cibertec.cl1.domain.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ABEL
 */
public interface UsuarioService {
    List<Usuario> findAll();
    public void guardarUsuario(Usuario usuario);
    Optional<Usuario> findbyId(Integer id);
    Optional<Usuario> findByEmail(String email);
}
