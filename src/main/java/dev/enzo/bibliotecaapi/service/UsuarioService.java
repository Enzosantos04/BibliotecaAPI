package dev.enzo.bibliotecaapi.service;

import dev.enzo.bibliotecaapi.dto.UsuarioDTO;
import dev.enzo.bibliotecaapi.mapper.UsuarioMapper;
import dev.enzo.bibliotecaapi.model.Usuario;

import dev.enzo.bibliotecaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::map)
                .collect(Collectors.toList());
    }

    public UsuarioDTO adicionarUsuario(UsuarioDTO usuarioDTO){
        Usuario novoUsuario = usuarioMapper.map(usuarioDTO);
        novoUsuario = usuarioRepository.save(novoUsuario);
        return usuarioMapper.map(novoUsuario);
    }

    public UsuarioDTO listarUsurioPorId(Long id){
        Optional<Usuario> usuarioPorId = usuarioRepository.findById(id);
        return usuarioPorId.map(usuarioMapper::map).orElse(null);
    }

    public void deletarPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizarUsuarioPorId(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if(usuarioExistente.isPresent()){
            Usuario usuarioAtualizado = usuarioMapper.map(usuarioDTO);
            usuarioAtualizado.setId(id);
            Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);
            return usuarioMapper.map(usuarioSalvo);
        }
        return null;
    }







}
