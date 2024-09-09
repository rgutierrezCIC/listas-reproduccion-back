package es.cic.curso08.listas_reproduccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso08.listas_reproduccion.model.Video;
import es.cic.curso08.listas_reproduccion.repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    // Obtener todos los videos
    public List<Video> obtenerTodos() {
        return videoRepository.findAll();
    }

    // Obtener un video por ID
    public Optional<Video> obtenerPorId(Long id) {
        return videoRepository.findById(id);
    }

    // Buscar por título
    public List<Video> buscarPorTitulo(String titulo) {
        return videoRepository.findByTituloContaining(titulo);
    }

    // Buscar por autor
    public List<Video> buscarPorAutor(String autor) {
        return videoRepository.findByAutor(autor);
    }

    // Buscar por clasificación
    public List<Video> buscarPorClasificacion(String clasificacion) {
        return videoRepository.findByClasificacion(clasificacion);
    }

    // Guardar o actualizar un video
    public Video guardarVideo(Video video) {
        return videoRepository.save(video);
    }

    // Eliminar un video por ID
    public void eliminarVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
