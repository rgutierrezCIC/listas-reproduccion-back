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
import es.cic.curso08.listas_reproduccion.repository.TematicaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class TematicaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TematicaRepository tematicaRepository;

    @BeforeEach
    public void setUp() {
        tematicaRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba
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
}
