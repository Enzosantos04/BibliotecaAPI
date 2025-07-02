package dev.enzo.bibliotecaapi.controller;


import dev.enzo.bibliotecaapi.dto.UsuarioDTO;
import dev.enzo.bibliotecaapi.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
       List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
       return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarUsuarios(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuariosDTO = usuarioService.adicionarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario de " + usuariosDTO.getId() + " Criado com sucesso.");
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Long id){
        if(usuarioService.listarUsurioPorId(id) != null){
           UsuarioDTO usuarioDTO = usuarioService.listarUsurioPorId(id);
           return ResponseEntity.status(HttpStatus.FOUND).body("Usuario de id " + usuarioDTO.getId() + " encontrado com sucesso. " + usuarioDTO.getEmail());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario de " + id + " nao existe.");
        }
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuarioPorId(@PathVariable Long id){
        if(usuarioService.listarUsurioPorId(id) != null){
           usuarioService.deletarPorId(id);
            return ResponseEntity.ok("O usuario de id " + id + " deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario de " + id + " nao encontrado.");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarUsuarioPorId(@PathVariable Long id, @RequestBody UsuarioDTO usuarioAtualizado){
        if(usuarioService.listarUsurioPorId(id) != null){
            UsuarioDTO usuarioDTO = usuarioService.atualizarUsuarioPorId(id, usuarioAtualizado);
            return ResponseEntity.ok("Usuario de id " + usuarioDTO.getId() + " atualizado com sucesso.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario de " + id + " nao encontrado.");

        }
    }
}
