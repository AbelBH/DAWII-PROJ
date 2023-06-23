/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.cl1.Dao;

import com.cibertec.cl1.domain.Orden;
import com.cibertec.cl1.domain.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ABEL
 */
public interface OrdenDao extends CrudRepository<Orden, Integer> {
    List<Orden> findByUsuario(Usuario usuario);
    
}
