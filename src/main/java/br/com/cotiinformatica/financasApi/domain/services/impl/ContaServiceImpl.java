package br.com.cotiinformatica.financasApi.domain.services.impl;

import br.com.cotiinformatica.financasApi.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.financasApi.domain.services.interfaces.ContaService;
import br.com.cotiinformatica.financasApi.infraestructure.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired ContaRepository contaRepository;

    @Override
    public ContaResponseDto cadastrar(ContaRequestDto request) throws Exception {
        return null;
    }

    @Override
    public ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception {
        return null;
    }

    @Override
    public ContaResponseDto excluir(Integer id) throws Exception {
        return null;
    }

    @Override
    public ContaResponseDto consultarPorId(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<ContaResponseDto> consultarTodos() throws Exception {
        return List.of();
    }
}
