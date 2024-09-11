package es.cic.curso08.listas_reproduccion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.cic.curso08.listas_reproduccion.model.Video;
import es.cic.curso08.listas_reproduccion.service.VideoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    // Obtener todos los videos
    @GetMapping
    public List<Video> obtenerTodos(@RequestParam(required = false) Long tematicaId) {
        if (tematicaId != null) {
            return videoService.buscarPorTematicaId(tematicaId);
        }
        return videoService.obtenerTodos();
    }

    // Obtener un video por ID
    @GetMapping("/{id}")
    public ResponseEntity<Video> obtenerPorId(@PathVariable Long id) {
        Optional<Video> video = videoService.obtenerPorId(id);
        return video.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Buscar videos por título
    @GetMapping("/buscar/titulo")
    public List<Video> buscarPorTitulo(@RequestParam String titulo) {
        return videoService.buscarPorTitulo(titulo);
    }

    // Buscar videos por autor
    @GetMapping("/buscar/autor")
    public List<Video> buscarPorAutor(@RequestParam String autor) {
        return videoService.buscarPorAutor(autor);
    }

    // Buscar videos por clasificación
    @GetMapping("/buscar/clasificacion")
    public List<Video> buscarPorClasificacion(@RequestParam String clasificacion) {
        return videoService.buscarPorClasificacion(clasificacion);
    }

    // Crear un nuevo video
    @PostMapping
    public ResponseEntity<Video> crearVideo(@Valid @RequestBody Video video) {
        try {
            Video nuevoVideo = videoService.guardarVideo(video);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVideo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar un video existente
    @PutMapping("/{id}")
    public ResponseEntity<Video> actualizarVideo(@PathVariable Long id,
            @Valid @RequestBody Video videoActualizado) {
        return videoService.obtenerPorId(id)
                .map(videoExistente -> {
                    videoExistente.setTitulo(videoActualizado.getTitulo());
                    videoExistente.setAutor(videoActualizado.getAutor());
                    videoExistente.setDuracion(videoActualizado.getDuracion());
                    videoExistente.setCalidad(videoActualizado.getCalidad());
                    videoExistente.setFechaCreacion(videoActualizado.getFechaCreacion());
                    videoExistente.setTematica(videoActualizado.getTematica());
                    videoExistente.setClasificacion(videoActualizado.getClasificacion());
                    Video actualizado = videoService.guardarVideo(videoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un video
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        videoService.eliminarVideo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}