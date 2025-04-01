package br.com.cotiinformatica.financasApi.domain.services.impl;

import java.util.List;

import br.com.cotiinformatica.financasApi.domain.models.entities.Tipo;
import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.TipoResponseDto;
import br.com.cotiinformatica.financasApi.domain.services.interfaces.TipoService;
import br.com.cotiinformatica.financasApi.infraestructure.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoServiceImpl implements TipoService {

    @Autowired
    TipoRepository tipoRepository;
    @Override
    public TipoResponseDto cadastrar(TipoRequestDto request) throws Exception {

        var tipo = new Tipo(); //instanciando um objeto da classe Tipo
        tipo.setNome(request.getNome()); //capturando o campo nome

        tipoRepository.save(tipo); //gravando o tipo no banco de dados

        //retornando os dados do tipo cadastrado
        return toResponse(tipo);
    }
    @Override
    public TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception {
        //verificar se o tipo ja existe no banco de dados
        var registro = tipoRepository.findById(id);

        //se o tipo nao existir
        if(registro.isEmpty()) {
            throw new IllegalArgumentException("Tipo não encontrado, verifique o ID informado.");
        }

        //capturando os dados do tipo existente
        var tipo = registro.get();

        //alterar os dados do tipo
        tipo.setNome(request.getNome());

        //gravando as alteracoes no banco de dados
        tipoRepository.save(tipo);

        //retornando os dados do tipo cadastrado
        return toResponse(tipo);
    }
    @Override
    public TipoResponseDto excluir(Integer id) throws Exception {
        //verificar se o tipo ja existe no banco de dados
        var registro = tipoRepository.findById(id);

        //se o tipo nao existir
        if(registro.isEmpty()) {
            throw new IllegalArgumentException("Tipo não encontrado, verifique o ID informado.");
        }

        //capturando os dados do tipo existente
        var tipo = registro.get();

        //excluindo o tipo do banco de dados
        tipoRepository.delete(tipo);

        //retornando os dados do tipo cadastrado
        return toResponse(tipo);
    }

    @Override
    public TipoResponseDto consultarPorId(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TipoResponseDto> consultarTodos() throws Exception {
        return List.of();
    }

    private TipoResponseDto toResponse(Tipo tipo) {
        //criando o objeto response para devolver os dados do tipo cadastrado
        var response = new TipoResponseDto();
        response.setId(tipo.getId());
        response.setNome(tipo.getNome());

        return response;
    }
}



