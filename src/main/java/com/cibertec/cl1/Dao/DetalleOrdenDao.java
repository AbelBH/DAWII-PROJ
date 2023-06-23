/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cibertec.cl1.Dao;

import com.cibertec.cl1.domain.DetalleOrden;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ABEL
 */
public interface DetalleOrdenDao extends CrudRepository<DetalleOrden, Integer> {
    
}
