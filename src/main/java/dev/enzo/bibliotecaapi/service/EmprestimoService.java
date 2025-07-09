package dev.enzo.bibliotecaapi.service;

import dev.enzo.bibliotecaapi.dto.EmprestimoDTO;
import dev.enzo.bibliotecaapi.dto.LivroDTO;
import dev.enzo.bibliotecaapi.dto.UsuarioDTO;
import dev.enzo.bibliotecaapi.mapper.EmprestimoMapper;
import dev.enzo.bibliotecaapi.model.Emprestimo;
import dev.enzo.bibliotecaapi.model.Livro;
import dev.enzo.bibliotecaapi.model.Usuario;
import dev.enzo.bibliotecaapi.repository.EmprestimoRepository;
import dev.enzo.bibliotecaapi.repository.LivroRepository;
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
    private LivroRepository livroRepository;;


    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper, UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public List<EmprestimoDTO> listarEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream()
                .map(emprestimoMapper::map)
                .collect(Collectors.toList());
    }

    public EmprestimoDTO criarEmprestimo(EmprestimoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = emprestimoMapper.map(dto);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusMonths(1));

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

    public void deletarEmprestimo(Long id){
        emprestimoRepository.deleteById(id);
    }

    public EmprestimoDTO listarEmprestimoPorId(Long id){
        Optional<Emprestimo> emprestimoPorId = emprestimoRepository.findById(id);
        return emprestimoPorId.map(emprestimoMapper::map).orElse(null);
    }
}
