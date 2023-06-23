package com.cibertec.cl1.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ABEL
 */
@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private String tipo;
    
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
    
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
}
