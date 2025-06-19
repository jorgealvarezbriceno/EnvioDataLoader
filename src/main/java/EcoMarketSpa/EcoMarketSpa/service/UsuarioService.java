package EcoMarketSpa.EcoMarketSpa.service;

import EcoMarketSpa.EcoMarketSpa.model.Usuario;
import EcoMarketSpa.EcoMarketSpa.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.transaction.Transactional;




@Transactional
@Service

public class UsuarioService {

    @Autowired
        private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();

    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usu){
        return usuarioRepository.save(usu);
    }

    public void delete(Integer id){
        usuarioRepository.deleteById(id);
    }


}

