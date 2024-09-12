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
public class VideoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TematicaRepository tematicaRepository;

    private Tematica tematica;
    private Video video;

    @BeforeEach
    public void setUp() {
        videoRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba
        tematicaRepository.deleteAll(); // Limpiar la base de datos de temáticas antes de cada prueba

        // Crear una temática para la prueba
        tematica = new Tematica();
        tematica.setNombre("Temática de Prueba");
        tematica.setDescripcion("Descripción de Temática de Prueba");
        tematica.setPopularidad(0);
        tematica.setActivo(true);
        tematica.setFechaCreacion(LocalDate.now());
        tematica = tematicaRepository.save(tematica);

        // Crear un video para la prueba
        video = new Video();
        video.setTitulo("Video a Actualizar");
        video.setAutor("Autor");
        video.setDuracion(60);
        video.setCalidad("720p");
        video.setFechaCreacion(LocalDate.now());
        video.setClasificacion("12");
        video.setTematica(tematica);
        video = videoRepository.save(video);
    }

    @Test
    public void testCrearVideo() throws Exception {
        String jsonContent = "{ \"titulo\": \"Video Nuevo\", \"autor\": \"Autor Nuevo\", \"duracion\": 150, \"calidad\": \"4K\", \"fechaCreacion\": \"2024-09-09\", \"clasificacion\": \"18\", \"tematica\": { \"id\": " + tematica.getId() + " } }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Video Nuevo"));
    }

    @Test
    public void testActualizarVideo() throws Exception {
        String jsonContent = "{ \"titulo\": \"Video Actualizado\", \"autor\": \"Autor Actualizado\", \"duracion\": 90, \"calidad\": \"1080p\", \"fechaCreacion\": \"2024-09-09\", \"clasificacion\": \"16\", \"tematica\": { \"id\": " + tematica.getId() + " } }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/videos/" + video.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Video Actualizado"));
    }

    @Test
    public void testObtenerTodos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].titulo").value("Video a Actualizar"));
    }

    @Test
    public void testObtenerPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/" + video.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Video a Actualizar"));
    }

    @Test
    public void testEliminarVideo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/videos/" + video.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/" + video.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBuscarPorTitulo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/buscar/titulo")
                .param("titulo", "Video a Actualizar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].titulo").value("Video a Actualizar"));
    }

    @Test
    public void testBuscarPorAutor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/buscar/autor")
                .param("autor", "Autor"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].autor").value("Autor"));
    }

    @Test
    public void testBuscarPorClasificacion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/buscar/clasificacion")
                .param("clasificacion", "12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].clasificacion").value("12"));
    }
}