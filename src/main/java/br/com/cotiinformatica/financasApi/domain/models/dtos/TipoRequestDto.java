package br.com.cotiinformatica.financasApi.domain.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoRequestDto {

    @Size(min = 6, max = 50, message = "O campo nome deve ter entre 6 e 50 caracteres")
    @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;
}
