package EcoMarketSpa.EcoMarketSpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcoMarketSpa.EcoMarketSpa.model.Comprobante;
import EcoMarketSpa.EcoMarketSpa.service.ComprobanteService;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {
    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping
    public ResponseEntity<List<Comprobante>> listar() {
        List<Comprobante> comprobantes = comprobanteService.buscarTodo();
        if (comprobantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comprobantes);
    }

    @PostMapping
    public ResponseEntity<Comprobante> crearFactura(@RequestBody Comprobante comprobante) {
        Comprobante nuevoComprobante = comprobanteService.agregarComprobante(comprobante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComprobante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFactura(@PathVariable Long id) {
        try {
            comprobanteService.borrarComprobante(id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
