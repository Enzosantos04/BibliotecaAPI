package dev.enzo.bibliotecaapi.service;


import dev.enzo.bibliotecaapi.mapper.LivroMapper;
import dev.enzo.bibliotecaapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    private LivroRepository livroRepository;
    private LivroMapper livroMapper;
}
