package dev.enzo.bibliotecaapi.mapper;

import dev.enzo.bibliotecaapi.dto.EmprestimoDTO;
import dev.enzo.bibliotecaapi.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {




    public EmprestimoDTO map(Emprestimo emprestimo) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setId(emprestimo.getId());
        dto.setUsuarioId(emprestimo.getUsuario().getId());
        dto.setLivroId(emprestimo.getLivro().getId());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataDevolucao(emprestimo.getDataDevolucao());
        dto.setDataDevolucaoReal(emprestimo.getDataDevolucaoReal());
        return dto;
    }


    public Emprestimo map(EmprestimoDTO dto) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(dto.getId());
        emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        emprestimo.setDataDevolucao(dto.getDataDevolucao());
        emprestimo.setDataDevolucaoReal(dto.getDataDevolucaoReal());
        return emprestimo;
    }
}
