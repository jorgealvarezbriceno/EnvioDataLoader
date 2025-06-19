package EcoMarketSpa.EcoMarketSpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcoMarketSpa.EcoMarketSpa.model.Usuario;
import EcoMarketSpa.EcoMarketSpa.service.UsuarioService;

import java.util.List;



@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>>listar(){
        List<Usuario>usuario=usuarioService.findAll();
        if (usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }


    @GetMapping("{id}")
    public ResponseEntity<Usuario>buscar(@PathVariable Integer id){
        try{
            Usuario usu = usuarioService.findById(id);
            return ResponseEntity.ok(usu);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try{
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

        }

        @PutMapping ("/{id}")
        public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id,@RequestBody Usuario usuario){
            try{ 
            Usuario usu=usuarioService.findById(id);
            usu.setId(id);
            usu.setNombres(usu.getNombres());
            usu.setApellidos(usu.getApellidos());
            usu.setCorreo(usu.getCorreo());
            usu.setEdad(usu.getEdad());
            usu.setContraseña(usu.getContraseña());

            usuarioService.save(usu);
            return ResponseEntity.ok(usu);
        } catch(Exception e){
            return ResponseEntity.notFound().build();

        }
 }
}







    


