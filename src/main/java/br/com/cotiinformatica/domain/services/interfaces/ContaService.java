package br.com.cotiinformatica.domain.services.interfaces;

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;


import java.util.List;

public interface ContaService {

    ContaResponseDto cadastrar(ContaRequestDto request) throws Exception;
    ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception;
    ContaResponseDto excluir(Integer id) throws Exception;
    ContaResponseDto consultarPorId(Integer id) throws Exception;
    List<ContaResponseDto> consultarTodos() throws Exception;
}
