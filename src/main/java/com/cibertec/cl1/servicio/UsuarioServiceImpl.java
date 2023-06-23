package com.cibertec.cl1.servicio;

import com.cibertec.cl1.Dao.UsuarioDao;
import com.cibertec.cl1.domain.Usuario;
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
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Optional<Usuario> findbyId(Integer id) {
        return usuarioDao.findById(id);
    }

    @Override
    @Transactional
    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }
    
    
}
