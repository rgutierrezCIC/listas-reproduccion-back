package es.cic.curso08.listas_reproduccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso08.listas_reproduccion.model.Tematica;
import es.cic.curso08.listas_reproduccion.repository.TematicaRepository;

@Service
public class TematicaService {

    @Autowired
    private TematicaRepository tematicaRepository;

    // Obtener todas las temáticas
    public List<Tematica> obtenerTodas() {
        return tematicaRepository.findAll();
    }

    // Obtener una temática por ID
    public Optional<Tematica> obtenerPorId(Long id) {
        return tematicaRepository.findById(id);
    }

    // Crear o actualizar una temática
    public Tematica guardarTematica(Tematica tematica) {
        return tematicaRepository.save(tematica);
    }

    // Eliminar una temática por ID
    public void eliminarTematica(Long id) {
        tematicaRepository.deleteById(id);
    }

    // Actualizar la popularidad de una temática (ejemplo si necesitas incrementar popularidad)
    public void incrementarPopularidad(Long id) {
        Tematica tematica = tematicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Temática no encontrada"));
        tematica.setPopularidad(tematica.getPopularidad() + 1);
        tematicaRepository.save(tematica);
    }

    // Decrementar popularidad
    public void decrementarPopularidad(Long id) {
        Tematica tematica = tematicaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Temática no encontrada"));
        tematica.setPopularidad(tematica.getPopularidad() - 1);
        tematicaRepository.save(tematica);
    }
}
