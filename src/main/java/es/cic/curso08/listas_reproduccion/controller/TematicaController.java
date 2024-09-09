package es.cic.curso08.listas_reproduccion.controller;

import java.util.List;

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

import es.cic.curso08.listas_reproduccion.model.Tematica;
import es.cic.curso08.listas_reproduccion.service.TematicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tematicas")
public class TematicaController {

    @Autowired
    private TematicaService tematicaService;

    // Obtener todas las temáticas
    @GetMapping
    public List<Tematica> obtenerTodas() {
        return tematicaService.obtenerTodas();
    }

    // Obtener una temática por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tematica> obtenerPorId(@PathVariable Long id) {
        return tematicaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva temática
    @PostMapping
    public ResponseEntity<Tematica> crearTematica(@Valid @RequestBody Tematica tematica) {
        Tematica nuevaTematica = tematicaService.guardarTematica(tematica);
        return ResponseEntity.ok(nuevaTematica);
    }

    // Actualizar una temática existente
    @PutMapping("/{id}")
    public ResponseEntity<Tematica> actualizarTematica(@PathVariable Long id, @Valid @RequestBody Tematica tematicaActualizada) {
        return tematicaService.obtenerPorId(id)
                .map(tematicaExistente -> {
                    tematicaExistente.setNombre(tematicaActualizada.getNombre());
                    tematicaExistente.setDescripcion(tematicaActualizada.getDescripcion());
                    tematicaExistente.setActivo(tematicaActualizada.getActivo());
                    tematicaExistente.setPopularidad(tematicaActualizada.getPopularidad());
                    tematicaExistente.setFechaCreacion(tematicaActualizada.getFechaCreacion());
                    Tematica actualizada = tematicaService.guardarTematica(tematicaExistente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una temática por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTematica(@PathVariable Long id) {
        if (tematicaService.obtenerPorId(id).isPresent()) {
            tematicaService.eliminarTematica(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
