package EcoMarketSpa.EcoMarketSpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import EcoMarketSpa.EcoMarketSpa.model.CarritoCompra;
import EcoMarketSpa.EcoMarketSpa.service.CarritoService;



@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<CarritoCompra> crear(@RequestBody CarritoCompra carrito) {
        return ResponseEntity.ok(carritoService.crear(carrito));    

    }
    @GetMapping
    public ResponseEntity<List<CarritoCompra>> listar() {
        return ResponseEntity.ok(carritoService.listar());
    }    

    public CarritoCompra obtenerPorId(@PathVariable Long id) {
    return carritoService.obtenerPorId(id).orElse(null);
}

    @PutMapping("/{carritoId}/agregar/{productoId}")
    public ResponseEntity<CarritoCompra> agregarProducto(
            @PathVariable Long carritoId,
            @PathVariable Long productoId) {
        return ResponseEntity.ok(carritoService.agregarProducto(carritoId, productoId));
    }


    @PutMapping("/{carritoId}/eliminar/{productoId}")
    public ResponseEntity<CarritoCompra> eliminarProducto(
            @PathVariable Long carritoId,
            @PathVariable Long productoId) {
        return ResponseEntity.ok(carritoService.eliminarProducto(carritoId, productoId));
    }

    
}
