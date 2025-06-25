package dev.enzo.bibliotecaapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoPublicacao;

}
