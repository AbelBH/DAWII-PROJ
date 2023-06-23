package com.cibertec.cl1.servicio;

import com.cibertec.cl1.domain.Orden;
import com.cibertec.cl1.domain.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ABEL
 */
public interface OrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save (Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
}
