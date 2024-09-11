package es.cic.curso08.listas_reproduccion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso08.listas_reproduccion.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTituloContaining(String titulo);
    List<Video> findByAutorContaining(String autor);
    List<Video> findByClasificacion(String clasificacion);
    List<Video> findByTematicaId(Long tematicaId);
}