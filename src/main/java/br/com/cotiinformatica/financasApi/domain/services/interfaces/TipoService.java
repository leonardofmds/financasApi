package br.com.cotiinformatica.financasApi.domain.services.interfaces;

import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoResponseDto;

import java.util.List;

public interface TipoService {

    TipoResponseDto cadastrar(TipoRequestDto request) throws Exception;
    TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception;
    TipoResponseDto excluir(Integer id) throws Exception;
    TipoResponseDto consultarPorId(Integer id) throws Exception;
    List<TipoResponseDto> consultarTodos() throws Exception;
}
