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

import es.cic.curso08.listas_reproduccion.model.Video;
import es.cic.curso08.listas_reproduccion.repository.VideoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class VideoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VideoRepository videoRepository;

    @BeforeEach
    public void setUp() {
        videoRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba
    }

    @Test
    public void testObtenerTodos() throws Exception {
        // Crear un video para la prueba
        Video video = new Video();
        video.setTitulo("Video 1");
        video.setAutor("Autor 1");
        video.setDuracion(120);
        video.setCalidad("1080p");
        video.setFechaCreacion(LocalDate.now());
        video.setClasificacion("16");
        videoRepository.save(video);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].titulo").value("Video 1"));
    }

    @Test
    public void testObtenerPorId() throws Exception {
        // Crear un video para la prueba
        Video video = new Video();
        video.setTitulo("Video 1");
        video.setAutor("Autor 1");
        video.setDuracion(120);
        video.setCalidad("1080p");
        video.setFechaCreacion(LocalDate.now());
        video.setClasificacion("16");
        video = videoRepository.save(video);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/" + video.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Video 1"));
    }

    @Test
    public void testCrearVideo() throws Exception {
        String jsonContent = "{ \"titulo\": \"Video Nuevo\", \"autor\": \"Autor Nuevo\", \"duracion\": 150, \"calidad\": \"4K\", \"fechaCreacion\": \"2024-09-09\", \"clasificacion\": \"18\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Video Nuevo"));
    }

    @Test
    public void testEliminarVideo() throws Exception {
        // Crear un video para la prueba
        Video video = new Video();
        video.setTitulo("Video a Eliminar");
        video.setAutor("Autor");
        video.setDuracion(60);
        video.setCalidad("720p");
        video.setFechaCreacion(LocalDate.now());
        video.setClasificacion("12");
        video = videoRepository.save(video);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/videos/" + video.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/videos/" + video.getId()))
                .andExpect(status().isNotFound());
    }
}
