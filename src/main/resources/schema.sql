CREATE TABLE IF NOT EXISTS tematica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500),
    popularidad INT DEFAULT 0,
    activo BOOLEAN NOT NULL,
    fecha_creacion DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS video(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    duracion INT, -- Duración en minutos
    calidad VARCHAR(50),
    fecha_creacion DATE NOT NULL,
    tematica_id BIGINT NOT NULL,
    clasificacion VARCHAR(2),
    FOREIGN KEY (tematica_id) REFERENCES tematica(id) ON DELETE RESTRICT,
    CHECK (clasificacion IN ('TP', '7', '12', '16', '18'))
);
