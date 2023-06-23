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
@Table(name = "tb_detalle")
public class DetalleOrden implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;
    
    @ManyToOne
    private Orden orden;
    
    @ManyToOne
    private Producto producto;
}
