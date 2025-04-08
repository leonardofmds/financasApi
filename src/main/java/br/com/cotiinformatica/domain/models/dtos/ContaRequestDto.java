package br.com.cotiinformatica.domain.models.dtos;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContaRequestDto {


    @Size(min = 6, max = 100, message = "O campo nome deve ter entre 6 e 100 caracteres")
    @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;

    @NotEmpty(message = "O campo data é obrigatório")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
            message = "Por favor, informe a data no formato dd/MM/yyyy.")
    private String data;

    @DecimalMin(value = "0.01", message = "O campo valor deve ser maior que 0")
    @NotNull
    private Double valor;

    @NotNull
    @Pattern(regexp = "^[12]$", message = "O tipo de movimentação deve ser '1' (Receita) ou '2' (Despesa).")
    private Integer movimentacao;
}
