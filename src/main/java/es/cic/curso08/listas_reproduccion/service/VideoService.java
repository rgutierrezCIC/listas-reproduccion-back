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

    public List<Video> obtenerTodos() {
        return videoRepository.findAll();
    }

    public Optional<Video> obtenerPorId(Long id) {
        return videoRepository.findById(id);
    }

    public List<Video> buscarPorTitulo(String titulo) {
        return videoRepository.findByTituloContaining(titulo);
    }

    public List<Video> buscarPorAutor(String autor) {
        return videoRepository.findByAutorContaining(autor);
    }

    public List<Video> buscarPorClasificacion(String clasificacion) {
        return videoRepository.findByClasificacion(clasificacion);
    }

    public List<Video> buscarPorTematicaId(Long tematicaId) {
        return videoRepository.findByTematicaId(tematicaId);
    }

    public boolean existenVideosConTematica(Long tematicaId) {
        return !videoRepository.findByTematicaId(tematicaId).isEmpty();
    }

    public Video guardarVideo(Video video) {
        return videoRepository.save(video);
    }

    public void eliminarVideo(Long id) {
        videoRepository.deleteById(id);
    }
}