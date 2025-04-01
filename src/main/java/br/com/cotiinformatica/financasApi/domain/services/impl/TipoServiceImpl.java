package br.com.cotiinformatica.financasApi.domain.services.impl;

import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoResponseDto;
import br.com.cotiinformatica.financasApi.domain.services.interfaces.TipoService;
import br.com.cotiinformatica.financasApi.infraestructure.repositories.ContaRepository;
import br.com.cotiinformatica.financasApi.infraestructure.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImpl implements TipoService {

    @Autowired TipoRepository tipoRepository;


    @Override
    public TipoResponseDto cadastrar(TipoRequestDto request) throws Exception {
        return null;
    }

    @Override
    public TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception {
        return null;
    }

    @Override
    public TipoResponseDto excluir(Integer id) throws Exception {
        return null;
    }

    @Override
    public TipoResponseDto consultarPorId(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<TipoResponseDto> consultarTodos() throws Exception {
        return List.of();
    }
}
