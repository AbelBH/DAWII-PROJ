package com.cibertec.cl1.servicio;

import com.cibertec.cl1.Dao.ProductoDao;
import com.cibertec.cl1.domain.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ABEL
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productodao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarproductos() {
        return (List<Producto>) productodao.findAll();
    }

    @Override
    @Transactional
    public void guardarProducto(Producto producto) {
        productodao.save(producto);
    }

    @Override
    @Transactional
    public void eliminarProducto(Producto producto) {
        productodao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto encontrarProducto(Producto producto) {
        return productodao.findById(producto.getId()).orElse(null);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productodao.findById(id);
    }
    
}
