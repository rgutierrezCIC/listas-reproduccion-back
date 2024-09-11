package es.cic.curso08.listas_reproduccion.Dto;

public class TematicaPopularidad {

    // pensado para una futura implementación de la funcionalidad de mostrar el número de videos por temática

    private Long id;
    private String nombre;
    private Long popularidad;

    public TematicaPopularidad(Long id, String nombre, Long popularidad) {
        this.id = id;
        this.nombre = nombre;
        this.popularidad = popularidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(Long popularidad) {
        this.popularidad = popularidad;
    }

    
}
