package com.cibertec.cl1.Dao;

import com.cibertec.cl1.domain.Producto;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ABEL
 */
public interface ProductoDao extends CrudRepository<Producto, Integer>{
    
}
