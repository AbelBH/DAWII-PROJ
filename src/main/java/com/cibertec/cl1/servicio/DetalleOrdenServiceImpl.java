package com.cibertec.cl1.servicio;

import com.cibertec.cl1.Dao.DetalleOrdenDao;
import com.cibertec.cl1.domain.DetalleOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ABEL
 */
@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

    @Autowired
    private DetalleOrdenDao detalleordenDao;
    
    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleordenDao.save(detalleOrden);
    }
    
}
