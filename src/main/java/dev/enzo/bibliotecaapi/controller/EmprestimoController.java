package dev.enzo.bibliotecaapi.controller;


import dev.enzo.bibliotecaapi.dto.EmprestimoDTO;
import dev.enzo.bibliotecaapi.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emprestimo")
public class EmprestimoController {
    EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmprestimoDTO>> listarEmprestimos() {
        List<EmprestimoDTO> emprestimos = emprestimoService.listarEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEmprestimos(@RequestBody EmprestimoDTO emprestimoDTO) {
        EmprestimoDTO emprestimosDTO = emprestimoService.criarEmprestimo(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimosDTO.getDataEmprestimo().toString());
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<EmprestimoDTO> devolverEmprestimo(@PathVariable Long id) {
        EmprestimoDTO emprestimoDTO = emprestimoService.devolverEmprestimo(id);
        return ResponseEntity.ok(emprestimoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEmprestimoPorId(@PathVariable Long id) {
        if (emprestimoService.listarEmprestimoPorId(id) != null) {
            emprestimoService.deletarEmprestimo(id);
            return ResponseEntity.ok("Emprestimo de id " + id + " deletado com sucesso");
        } else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo de id " + id + " nao encontrado");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarEmprestimoPorId(@PathVariable Long id){
            EmprestimoDTO emprestimoDTO = emprestimoService.listarEmprestimoPorId(id);
        if(emprestimoService.listarEmprestimoPorId(id) != null){
            return ResponseEntity.ok("Data Emprestimo: " + emprestimoDTO.getDataEmprestimo() + " Livro id: " +
                    emprestimoDTO.getLivroId() + " Usuario Id: " + emprestimoDTO.getUsuarioId() + " Data de Devolucao: " +
                    emprestimoDTO.getDataDevolucao());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emprestimo de id " + id + " nao encontrado");
        }
    }
}





