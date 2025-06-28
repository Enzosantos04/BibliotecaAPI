package dev.enzo.bibliotecaapi.service;


import dev.enzo.bibliotecaapi.dto.LivroDTO;
import dev.enzo.bibliotecaapi.mapper.LivroMapper;
import dev.enzo.bibliotecaapi.model.Livro;
import dev.enzo.bibliotecaapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {
    private LivroRepository livroRepository;
    private LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }


    public List<LivroDTO> listarLivros(){
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(livroMapper::map)
                .collect(Collectors.toList());
    }

        public LivroDTO adicionarLivros(LivroDTO livroDTO){
            Livro novoLivro = livroMapper.map(livroDTO);
            novoLivro = livroRepository.save(novoLivro);
            return livroMapper.map(novoLivro);
        }

    public LivroDTO listarLivrosPorId(Long id){
        Optional<Livro> livrosPorId = livroRepository.findById(id);
        return livrosPorId.map(livroMapper::map).orElse(null);
    }

    public void deletarLivroPorId(Long id){
        livroRepository.deleteById(id);
    }

    public LivroDTO atualizarLivroPorId(Long id, LivroDTO livroDTO){
        Optional<Livro> livroExistente = livroRepository.findById(id);
        if(livroExistente.isPresent()){
            Livro livroAtualizado = livroMapper.map(livroDTO);
            livroAtualizado.setId(id);
            Livro livroSalvo = livroRepository.save(livroAtualizado);
            return livroMapper.map(livroSalvo);
        }
        return null;
    }
}
