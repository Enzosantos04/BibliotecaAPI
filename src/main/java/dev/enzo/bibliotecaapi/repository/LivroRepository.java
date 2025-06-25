package dev.enzo.bibliotecaapi.repository;

import dev.enzo.bibliotecaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
