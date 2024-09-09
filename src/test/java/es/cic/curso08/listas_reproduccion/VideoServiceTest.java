package es.cic.curso08.listas_reproduccion;

import es.cic.curso08.listas_reproduccion.model.Video;
import es.cic.curso08.listas_reproduccion.repository.VideoRepository;
import es.cic.curso08.listas_reproduccion.service.VideoService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext
public class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoService videoService;

    public VideoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodos() {
        Video video1 = new Video();
        video1.setId(1L);
        video1.setTitulo("Video 1");

        Video video2 = new Video();
        video2.setId(2L);
        video2.setTitulo("Video 2");

        when(videoRepository.findAll()).thenReturn(Arrays.asList(video1, video2));

        List<Video> videos = videoService.obtenerTodos();

        assertEquals(2, videos.size());
        assertEquals("Video 1", videos.get(0).getTitulo());
    }

    @Test
    public void testObtenerPorId() {
        Video video = new Video();
        video.setId(1L);
        video.setTitulo("Video 1");

        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));

        Optional<Video> result = videoService.obtenerPorId(1L);

        assertEquals("Video 1", result.get().getTitulo());
    }
}
