package cl.example.tipoTrabajo.controller;

import cl.example.tipoTrabajo.dto.TipoTrabajoDTO;
import cl.example.tipoTrabajo.modelo.TipoTrabajo;
import cl.example.tipoTrabajo.service.TipoTrabajoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-trabajo")
public class TipoTrabajoController {

    @Autowired
    private TipoTrabajoService service;

    @GetMapping
    public List<TipoTrabajo> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTrabajo> obtenerPorId(@PathVariable Long id) {
        Optional<TipoTrabajo> tipo = service.obtenerPorId(id);
        return tipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoTrabajo guardarTipoTrabajo(@Valid @RequestBody TipoTrabajoDTO dto) {
        return service.guardarTipoTrabajo(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTrabajo> actualizarTipoTrabajo(@PathVariable Long id, @Valid @RequestBody TipoTrabajoDTO dto) {
        TipoTrabajo actualizado = service.actualizarTipoTrabajo(id, dto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoTrabajo(@PathVariable Long id) {
        service.eliminarTipoTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}