package es.cic.curso08.listas_reproduccion;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.cic.curso08.listas_reproduccion.model.Tematica;
import es.cic.curso08.listas_reproduccion.model.Video;
import es.cic.curso08.listas_reproduccion.repository.TematicaRepository;
import es.cic.curso08.listas_reproduccion.repository.VideoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class TematicaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TematicaRepository tematicaRepository;

    @Autowired
    private VideoRepository videoRepository;

    @BeforeEach
    public void setUp() {
        videoRepository.deleteAll(); // Limpiar la base de datos de videos antes de cada prueba
        tematicaRepository.deleteAll(); // Limpiar la base de datos de temáticas antes de cada prueba
    }

    @Test
    public void testObtenerTodas() throws Exception {
        // Crear una temática para la prueba
        Tematica tematica = new Tematica();
        tematica.setNombre("Temática 1");
        tematica.setDescripcion("Descripción de Temática 1");
        tematica.setPopularidad(0);
        tematica.setActivo(true);
        tematica.setFechaCreacion(LocalDate.now());
        tematicaRepository.save(tematica);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tematicas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Temática 1"));
    }

    @Test
    public void testObtenerPorId() throws Exception {
        // Crear una temática para la prueba
        Tematica tematica = new Tematica();
        tematica.setNombre("Temática 1");
        tematica.setDescripcion("Descripción de Temática 1");
        tematica.setPopularidad(0);
        tematica.setActivo(true);
        tematica.setFechaCreacion(LocalDate.now());
        tematica = tematicaRepository.save(tematica);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tematicas/" + tematica.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Temática 1"));
    }

    @Test
    public void testCrearTematica() throws Exception {
        String jsonContent = "{ \"nombre\": \"Temática Nueva\", \"descripcion\": \"Descripción de Temática Nueva\", \"fechaCreacion\": \"2024-09-09\", \"popularidad\": 0, \"activo\": true }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tematicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated()) // Espera el código 201
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Temática Nueva"));
    }

    @Test
    public void testActualizarTematica() throws Exception {
        // Crear una temática para la prueba
        Tematica tematica = new Tematica();
        tematica.setNombre("Temática a Actualizar");
        tematica.setDescripcion("Descripción de Temática a Actualizar");
        tematica.setPopularidad(0);
        tematica.setActivo(true);
        tematica.setFechaCreacion(LocalDate.now());
        tematica = tematicaRepository.save(tematica);

        String jsonContent = "{ \"nombre\": \"Temática Actualizada\", \"descripcion\": \"Descripción Actualizada\", \"fechaCreacion\": \"2024-09-09\", \"popularidad\": 1, \"activo\": false }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tematicas/" + tematica.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Temática Actualizada"));
    }

    @Test
    public void testEliminarTematica() throws Exception {
        // Crear una temática para la prueba
        Tematica tematica = new Tematica();
        tematica.setNombre("Temática a Eliminar");
        tematica.setDescripcion("Descripción de Temática a Eliminar");
        tematica.setPopularidad(0);
        tematica.setActivo(true);
        tematica.setFechaCreacion(LocalDate.now());
        tematica = tematicaRepository.save(tematica);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tematicas/" + tematica.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tematicas/" + tematica.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEliminarTematicaConVideos() throws Exception {
        // Crear una temática para la prueba
        Tematica tematica = new Tematica();
        tematica.setNombre("Temática con Videos");
        tematica.setDescripcion("Descripción de Temática con Videos");
        tematica.setPopularidad(0);
        tematica.setActivo(true);
        tematica.setFechaCreacion(LocalDate.now());
        tematica = tematicaRepository.save(tematica);

        // Crear un video asociado a la temática
        Video video = new Video();
        video.setTitulo("Video de Prueba");
        video.setAutor("Autor de Prueba");
        video.setDuracion(10);
        video.setCalidad("720p"); // Valor válido para calidad
        video.setClasificacion("TP"); // Valor válido para clasificación
        video.setFechaCreacion(LocalDate.now());
        video.setTematica(tematica);
        videoRepository.save(video);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tematicas/" + tematica.getId()))
                .andExpect(status().isConflict());
    }
}