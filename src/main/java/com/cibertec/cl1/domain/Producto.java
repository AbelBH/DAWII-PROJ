package com.cibertec.cl1.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ABEL
 */
@Data
@Entity
@Table(name = "tb_producto")
public class Producto implements Serializable {
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String imagen;
    
    @ManyToOne
    private Usuario usuario;
}
