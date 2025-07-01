package dev.enzo.bibliotecaapi.repository;

import dev.enzo.bibliotecaapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
