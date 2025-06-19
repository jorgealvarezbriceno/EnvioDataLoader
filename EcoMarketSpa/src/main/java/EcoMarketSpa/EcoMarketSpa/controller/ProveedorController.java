package EcoMarketSpa.EcoMarketSpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import EcoMarketSpa.EcoMarketSpa.service.ProveedorService;
import EcoMarketSpa.EcoMarketSpa.model.Proveedor;

import java.util.List;


@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor>listaProveedores(){
        return proveedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedoor(@PathVariable Long id){
        return proveedorService.obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.guardar(proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id){
        proveedorService.eliminar(id);
        return ResponseEntity.ok().build();
    }


}
