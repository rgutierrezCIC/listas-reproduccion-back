package es.cic.curso08.listas_reproduccion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.cic.curso08.listas_reproduccion.model.Tematica;
import es.cic.curso08.listas_reproduccion.service.TematicaService;
import es.cic.curso08.listas_reproduccion.service.VideoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tematicas")
public class TematicaController {

    @Autowired
    private TematicaService tematicaService;

    @Autowired
    private VideoService videoService;

    // Obtener todas las temáticas
    @GetMapping
    public List<Tematica> obtenerTodas() {
        return tematicaService.obtenerTodas();
    }

    // Obtener una temática por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tematica> obtenerPorId(@PathVariable Long id) {
        Optional<Tematica> tematica = tematicaService.obtenerPorId(id);
        return tematica.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva temática
    @PostMapping
    public ResponseEntity<Tematica> crearTematica(@Valid @RequestBody Tematica tematica) {
        try {
            Tematica nuevaTematica = tematicaService.guardarTematica(tematica);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTematica);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una temática existente
    @PutMapping("/{id}")
    public ResponseEntity<Tematica> actualizarTematica(@PathVariable Long id,
            @Valid @RequestBody Tematica tematicaActualizada) {
        return tematicaService.obtenerPorId(id)
                .map(tematicaExistente -> {
                    tematicaExistente.setNombre(tematicaActualizada.getNombre());
                    tematicaExistente.setDescripcion(tematicaActualizada.getDescripcion());
                    tematicaExistente.setPopularidad(tematicaActualizada.getPopularidad());
                    tematicaExistente.setActivo(tematicaActualizada.getActivo());
                    tematicaExistente.setFechaCreacion(tematicaActualizada.getFechaCreacion());
                    Tematica actualizada = tematicaService.guardarTematica(tematicaExistente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una temática
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTematica(@PathVariable Long id) {
        if (videoService.existenVideosConTematica(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        tematicaService.eliminarTematica(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}