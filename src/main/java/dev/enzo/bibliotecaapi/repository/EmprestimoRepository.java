package dev.enzo.bibliotecaapi.repository;

import dev.enzo.bibliotecaapi.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
