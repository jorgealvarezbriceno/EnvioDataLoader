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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import EcoMarketSpa.EcoMarketSpa.service.EnvioService;
import EcoMarketSpa.EcoMarketSpa.model.Envio;
import EcoMarketSpa.EcoMarketSpa.model.Envio.EstadoEnvio;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/envios")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @PostMapping("/{clienteId}")
    public ResponseEntity<Envio> crearEnvioCliente(
            @PathVariable Integer clienteId,
            @RequestParam String fechaSalida,
            @RequestParam String fechaEntrega) {

        try {
            Envio envio = envioService.crearEnvioCliente(clienteId, fechaSalida, fechaEntrega);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<Envio> listarTodos() {
        return envioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Envio> buscarPorId(@PathVariable long id) {
        return envioService.obtenerPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Envio> buscarPorEstado(@PathVariable String estado) {
        return envioService.obtenerPorEstado(estado);
    }

    @PutMapping("/{id}/estado")
    public Envio actualizarEstado(@PathVariable Long id, @RequestBody EstadoEnvio nuevoEstado) {
        return envioService.actualizarEstado(id, nuevoEstado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEnvio(@PathVariable Long id) {
        envioService.eliminarEnvio(id);
    }

}


