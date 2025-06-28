package dev.enzo.bibliotecaapi.controller;


import dev.enzo.bibliotecaapi.dto.LivroDTO;
import dev.enzo.bibliotecaapi.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarLivro(@RequestBody LivroDTO livroDTO){
        LivroDTO livrosDTO = livroService.adicionarLivros(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(livrosDTO.getTitulo());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LivroDTO>> listarLivros(){
        List<LivroDTO> livros = livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarLivro(@PathVariable  Long id, @RequestBody LivroDTO livroAtualizado){
        if(livroService.listarLivrosPorId(id) != null){
            LivroDTO livroDTO = livroService.atualizarLivroPorId(id, livroAtualizado);
            return ResponseEntity.ok("Livro de ID " + livroDTO.getId() + " Atualizado com Sucesso.");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O livro de ID " + id + " nao encontrado.");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?>listarPorId(@PathVariable Long id){
        if(livroService.listarLivrosPorId(id) != null){
            LivroDTO livroDTO = livroService.listarLivrosPorId(id);
            return ResponseEntity.status(HttpStatus.FOUND).body("Livro: " + livroDTO.getTitulo());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O livro de ID " + id + " nao encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        if (livroService.listarLivrosPorId(id) != null){
           livroService.deletarLivroPorId(id);
            return ResponseEntity.ok("O livro de id " + id + " deletado com sucesso");

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O livro de ID " + id + " nao foi encontrado." );
        }
    }
}
