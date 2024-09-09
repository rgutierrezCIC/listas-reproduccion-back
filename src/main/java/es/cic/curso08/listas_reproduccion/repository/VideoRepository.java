package es.cic.curso08.listas_reproduccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.cic.curso08.listas_reproduccion.model.Video;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTituloContaining(String titulo);
    List<Video> findByAutor(String autor);
    List<Video> findByClasificacion(String clasificacion);
}

