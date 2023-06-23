package com.cibertec.cl1.Dao;

import com.cibertec.cl1.domain.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ABEL
 */
public interface UsuarioDao extends CrudRepository<Usuario, Integer>{
    Optional<Usuario> findByEmail(String email);
    
}
