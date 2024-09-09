
INSERT INTO tematica (nombre, descripcion, popularidad, activo, fecha_creacion) VALUES
('Tecnología', 'Videoblogs sobre tecnología y gadgets', 10, TRUE, '2023-01-01'),
('Viajes', 'Videoblogs sobre viajes y aventuras', 20, TRUE, '2023-02-01'),
('Cocina', 'Videoblogs sobre recetas y cocina', 5, TRUE, '2023-03-01'),
('Deportes', 'Videoblogs sobre deportes y fitness', 15, TRUE, '2023-04-01'),
('Educación', 'Videoblogs educativos y tutoriales', 8, TRUE, '2023-05-01'),
('Música', 'Videoblogs sobre música y conciertos', 12, TRUE, '2023-06-01'),
('Cine', 'Videoblogs sobre películas y series', 18, TRUE, '2023-07-01'),
('Moda', 'Videoblogs sobre moda y estilo', 7, TRUE, '2023-08-01'),
('Salud', 'Videoblogs sobre salud y bienestar', 9, TRUE, '2023-09-01'),
('Noticias', 'Videoblogs sobre noticias y actualidad', 11, TRUE, '2023-10-01');

INSERT INTO video (titulo, autor, duracion, calidad, fecha_creacion, tematica_id, clasificacion) VALUES
('Review del iPhone 13', 'Tech Guru', 15, 'HD', '2023-04-01', 1, '12'),
('Aventura en los Alpes', 'Travel Vlogger', 20, 'HD', '2023-05-01', 2, '7'),
('Receta de lasaña', 'Chef Master', 30, 'SD', '2023-06-01', 3, 'TP'),
('Entrenamiento HIIT', 'Fitness Pro', 25, 'HD', '2023-07-01', 4, '16'),
('Curso de Python', 'Edu Tech', 40, 'HD', '2023-08-01', 5, '12'),
('Concierto en vivo', 'Music Star', 60, 'HD', '2023-09-01', 6, '7'),
('Crítica de Inception', 'Movie Buff', 10, 'SD', '2023-10-01', 7, '12'),
('Tendencias de moda 2023', 'Fashionista', 18, 'HD', '2023-11-01', 8, '7'),
('Yoga para principiantes', 'Health Guru', 35, 'HD', '2023-12-01', 9, 'TP'),
('Resumen de noticias', 'News Anchor', 5, 'SD', '2023-01-01', 10, '12');