package es.cic.curso08.listas_reproduccion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cic.curso08.listas_reproduccion.Dto.TematicaPopularidad;
import es.cic.curso08.listas_reproduccion.model.Tematica;

public interface TematicaRepository extends JpaRepository<Tematica, Long> {
    List<Tematica> findByNombre(String nombre);

    @Query("SELECT new com.example.dto.TematicaPopularidad(t.id, t.nombre, COUNT(v.id)) " +
           "FROM Tematica t LEFT JOIN Video v ON t.id = v.tematica.id " +
           "GROUP BY t.id, t.nombre")
    List<TematicaPopularidad> findTematicaPopularidad();
}
