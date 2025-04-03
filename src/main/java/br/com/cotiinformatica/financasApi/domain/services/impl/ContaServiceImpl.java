package br.com.cotiinformatica.financasApi.domain.services.impl;

import br.com.cotiinformatica.financasApi.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.financasApi.domain.models.entities.Conta;
import br.com.cotiinformatica.financasApi.domain.models.enums.Movimentacao;
import br.com.cotiinformatica.financasApi.domain.services.interfaces.ContaService;
import br.com.cotiinformatica.financasApi.infraestructure.components.RabbitMQProducerComponent;
import br.com.cotiinformatica.financasApi.infraestructure.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired ContaRepository contaRepository;
    @Autowired RabbitMQProducerComponent producerComponent;

    @Override
    public ContaResponseDto cadastrar(ContaRequestDto request) throws Exception {
        var conta = new Conta(); //instanciando um objeto da classe Conta

        conta.setNome(request.getNome()); //capturando o campo nome;
        conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(request.getData())); //capturando o campo data;
        conta.setValor(BigDecimal.valueOf(request.getValor())); //capturando o campo valor;
        conta.setMovimentacao(request.getMovimentacao() == 1 //Se movimentação é igual a 1
                ? Movimentacao.RECEITA :  //então movimentação é Receita
                request.getMovimentacao() == 2 //Se movimentação é igual 2
                        ? Movimentacao.DESPESA //então movimentação é Despesa
                        : null); //senão é igual a vazio

        //gravando a conta no banco de dados
        contaRepository.save(conta);

        //enviando a conta para o servidor de mensageria
        producerComponent.sendMessage(conta);

        return toResponse(conta);
    }
    @Override
    public ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception {
        var registro = contaRepository.findById(id);

        if(registro.isEmpty())
            throw new IllegalArgumentException("Conta não encontrada, verifique o ID informado.");

        var conta = registro.get();

        conta.setNome(request.getNome());
        conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(request.getData()));
        conta.setValor(BigDecimal.valueOf(request.getValor()));
        conta.setMovimentacao(request.getMovimentacao() == 1
                ? Movimentacao.RECEITA :
                request.getMovimentacao() == 2
                        ? Movimentacao.DESPESA
                        : null);

        contaRepository.save(conta);

        return toResponse(conta);
    }
    @Override
    public ContaResponseDto excluir(Integer id) throws Exception {

        var registro = contaRepository.findById(id);
        if(registro.isEmpty())
            throw new IllegalArgumentException("Conta não encontrada, verifique o ID informado.");

        var conta = registro.get();
        contaRepository.delete(conta);
        return toResponse(conta);
    }
    @Override
    public List<ContaResponseDto> consultarTodos() throws Exception {
        // TODO Auto-generated method stub
        var contas = contaRepository.findAll();
        var result = new ArrayList<ContaResponseDto>();

        for (Conta conta : contas) {
            result.add(toResponse(conta));
        }

        return result;
    }
    @Override
    public ContaResponseDto consultarPorId(Integer id) throws Exception {
        // TODO Auto-generated method stub

        var registro = contaRepository.findById(id);

        if(registro.isEmpty()){
            throw new IllegalArgumentException("Conta não encontrada, verifique o ID informado.");
        }
        var conta = registro.get();
        return toResponse(conta);
    }

    private ContaResponseDto toResponse(Conta conta) {

        var response = new ContaResponseDto();

        response.setId(conta.getId());
        response.setNome(conta.getNome());
        response.setData(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData()));
        response.setValor(conta.getValor().doubleValue());
        response.setMovimentacao(conta.getMovimentacao().toString());

        return response;
    }
}



