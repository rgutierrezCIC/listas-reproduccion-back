package es.cic.curso08.listas_reproduccion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El título no puede ser nulo")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    private String titulo;

    @NotNull(message = "El autor no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre del autor debe tener entre 1 y 255 caracteres")
    private String autor;

    @Min(value = 1, message = "La duración debe ser al menos de 1 minuto")
    @Max(value = 1440, message = "La duración máxima es de 1440 minutos (24 horas)")
    private int duracion; // Duración en minutos

    @NotNull(message = "La calidad no puede ser nula")
    @Pattern(regexp = "^(240p|360p|480p|720p|1080p|4K|8K)$", message = "La calidad debe ser una de las siguientes: 240p, 360p, 480p, 720p, 1080p, 4K, 8K")
    private String calidad;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "tematica_id", nullable = false)
    @JsonIgnoreProperties("videos")
    private Tematica tematica; // Relación con la entidad Tematica

    @NotNull(message = "La clasificación no puede ser nula")
    @Pattern(regexp = "^(TP|7|12|16|18)$", message = "La clasificación debe ser una de las siguientes: TP, 7, 12, 16, 18")
    @Column(length = 2)
    private String clasificacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}