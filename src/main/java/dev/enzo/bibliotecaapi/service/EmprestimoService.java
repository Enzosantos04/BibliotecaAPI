package dev.enzo.bibliotecaapi.service;

import dev.enzo.bibliotecaapi.dto.EmprestimoDTO;
import dev.enzo.bibliotecaapi.dto.UsuarioDTO;
import dev.enzo.bibliotecaapi.mapper.EmprestimoMapper;
import dev.enzo.bibliotecaapi.model.Emprestimo;
import dev.enzo.bibliotecaapi.model.Usuario;
import dev.enzo.bibliotecaapi.repository.EmprestimoRepository;
import dev.enzo.bibliotecaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private EmprestimoMapper emprestimoMapper;
    private UsuarioRepository usuarioRepository;


    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }


    public List<EmprestimoDTO> listarEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream()
                .map(emprestimoMapper::map)
                .collect(Collectors.toList());
    }

    public EmprestimoDTO criarEmprestimo(EmprestimoDTO dto){
    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId().getId())
             .orElseThrow(() -> new RuntimeException("usuario nao encontrado"));
     Emprestimo emprestimo = emprestimoMapper.map(dto);
     emprestimo.setUsuario(usuario);
     emprestimo = emprestimoRepository.save(emprestimo);
     return emprestimoMapper.map(emprestimo);

    }

    public EmprestimoDTO devolverEmprestimo(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(()-> new RuntimeException("O usuario nao encontrado"));
        if (emprestimo.getDataDevolucaoReal() != null) {
            throw new RuntimeException("Este empréstimo já foi devolvido.");
        }
        emprestimo.setDataDevolucaoReal(LocalDate.now());
        emprestimo = emprestimoRepository.save(emprestimo);
        return emprestimoMapper.map(emprestimo);


    }
}
