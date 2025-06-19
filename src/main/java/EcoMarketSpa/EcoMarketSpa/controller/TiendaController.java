package EcoMarketSpa.EcoMarketSpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcoMarketSpa.EcoMarketSpa.model.Tienda;
import EcoMarketSpa.EcoMarketSpa.service.TiendaService;

import java.util.List;


@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    private final TiendaService tiendaService;

    public TiendaController(TiendaService tiendaService){
        this.tiendaService = tiendaService;
    }

    @GetMapping
    public List<Tienda> listarTiendas() {
        return tiendaService.listarTiendas();
    }

    @PostMapping 
    public Tienda crearTienda(@RequestBody Tienda tienda) {
        return tiendaService.guardarTienda(tienda);
    }

    @GetMapping("/{id}")
    public Tienda obtenerTienda (@PathVariable Long id){
        return tiendaService.obtenerTiendaPorId(id).orElse(null);
    }
    
     @DeleteMapping("/{id}")    
    public void eliminarTienda(@PathVariable Long id) {
    tiendaService.eliminarTienda(id);
    }


}
