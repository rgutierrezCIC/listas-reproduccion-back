package es.cic.curso08.listas_reproduccion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.cic.curso08.listas_reproduccion.model.Video;
import es.cic.curso08.listas_reproduccion.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    // Obtener todos los videos
    @GetMapping
    public List<Video> obtenerTodos() {
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

    // Crear o actualizar un video
    @PostMapping
    public ResponseEntity<Video> crearOActualizar(@RequestBody Video video) {
        Video videoGuardado = videoService.guardarVideo(video);
        return new ResponseEntity<>(videoGuardado, HttpStatus.CREATED);
    }

    // Eliminar un video
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        videoService.eliminarVideo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

