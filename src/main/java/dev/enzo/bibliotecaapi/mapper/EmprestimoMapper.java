package dev.enzo.bibliotecaapi.mapper;

import dev.enzo.bibliotecaapi.dto.EmprestimoDTO;
import dev.enzo.bibliotecaapi.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {
    public Emprestimo map(EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimoModel = new Emprestimo();
        emprestimoModel.setId(emprestimoDTO.getId());
        emprestimoModel.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimoModel.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        emprestimoModel.setDataDevolucaoReal(emprestimoDTO.getDataDevolucaoReal());
        emprestimoModel.setUsuario(emprestimoDTO.getUsuarioId());
        emprestimoModel.setLivro(emprestimoDTO.getLivroId());
        return emprestimoModel;
    }

    public EmprestimoDTO map(Emprestimo emprestimoModel){
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        emprestimoDTO.setId(emprestimoModel.getId());
        emprestimoDTO.setDataEmprestimo(emprestimoModel.getDataEmprestimo());
        emprestimoDTO.setDataDevolucao(emprestimoModel.getDataDevolucao());
        emprestimoDTO.setDataDevolucaoReal(emprestimoModel.getDataDevolucaoReal());
        emprestimoDTO.setLivroId(emprestimoModel.getLivro());
        emprestimoDTO.setUsuarioId(emprestimoModel.getUsuario());
        return emprestimoDTO;
    }
}
