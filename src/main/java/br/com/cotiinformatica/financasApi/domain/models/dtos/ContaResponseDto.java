package br.com.cotiinformatica.financasApi.domain.models.dtos;

import lombok.Data;

@Data
public class ContaResponseDto {

    private Integer id;
    private String nome;
    private String data;
    private Double valor;
    private String movimentacao;
}
