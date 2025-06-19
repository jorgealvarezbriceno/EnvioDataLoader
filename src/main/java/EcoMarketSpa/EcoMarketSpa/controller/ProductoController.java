package EcoMarketSpa.EcoMarketSpa.controller;

import EcoMarketSpa.EcoMarketSpa.service.ProductoService;
import EcoMarketSpa.EcoMarketSpa.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RequestMapping("/api/productos")
@RestController
public class ProductoController {
    @Autowired
        private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.notFound().build();
        } catch ( Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto (@RequestBody Producto producto) {
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable Integer id){
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
            
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,@RequestBody Producto producto){
        try {
            Producto pro = productoService.findById(id);
            pro.setId(id);
            pro.setNombre(producto.getNombre());
            pro.setCategoria(producto.getCategoria());
            pro.setFechaCaducidad(producto.getFechaCaducidad());
            pro.setFechaIngreso(producto.getFechaIngreso());
            pro.setPrecio(producto.getPrecio());
            pro.setStock(producto.getStock());

            productoService.save(pro);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> consultarStock(@PathVariable Long id) {
        Integer stock = productoService.consultarStock(id);
        if (stock == null) {
        return ResponseEntity.notFound().build();
    }
        return ResponseEntity.ok(stock);
    }


}




