package dev.enzo.bibliotecaapi.mapper;

import dev.enzo.bibliotecaapi.dto.UsuarioDTO;
import dev.enzo.bibliotecaapi.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario map(UsuarioDTO usuarioDTO){
        Usuario usuarioModel = new Usuario();
        usuarioModel.setId(usuarioDTO.getId());
        usuarioModel.setNome(usuarioDTO.getNome());
        usuarioModel.setEmail(usuarioDTO.getEmail());
        return usuarioModel;
    }


    public UsuarioDTO map(Usuario usuarioModel){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioModel.getId());
        usuarioDTO.setNome(usuarioModel.getNome());
        usuarioModel.setEmail(usuarioModel.getEmail());
        return usuarioDTO;
    }

}
