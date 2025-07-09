package dev.enzo.bibliotecaapi.dto;

import dev.enzo.bibliotecaapi.model.Livro;
import dev.enzo.bibliotecaapi.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {
    private Long id;
    private Long usuarioId;
    private Long livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolucaoReal;


}
