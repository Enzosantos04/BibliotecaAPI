package dev.enzo.bibliotecaapi.mapper;

import dev.enzo.bibliotecaapi.dto.LivroDTO;
import dev.enzo.bibliotecaapi.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro map(LivroDTO livroDTO){
        Livro livroModel = new Livro();
        livroModel.setId(livroDTO.getId());
        livroModel.setTitulo(livroDTO.getTitulo());
        livroModel.setAutor(livroDTO.getAutor());
        livroModel.setAnoPublicacao(livroDTO.getAnoPublicacao());
        return livroModel;
    }

    public LivroDTO map(Livro livroModel){
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livroModel.getId());
        livroDTO.setTitulo(livroModel.getTitulo());
        livroDTO.setAutor(livroModel.getAutor());
        livroDTO.setAnoPublicacao(livroModel.getAnoPublicacao());
        return livroDTO;

    }
}
