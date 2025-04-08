package br.com.cotiinformatica.financasApi.infraestructure.components;

import br.com.cotiinformatica.financasApi.domain.collections.LogFinancas;
import br.com.cotiinformatica.financasApi.domain.models.entities.Conta;
import br.com.cotiinformatica.financasApi.infraestructure.repositories.LogFinancasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class LogFinancasComponent {

    @Autowired LogFinancasRepository logFinancasRepository;

    /*
     * Método para gravar os dados do log de movimentação
     * no banco de dados MongoDB
     */

    public void gerarLog(Conta conta, Operacao operacao) {
        var logFinancas  = new LogFinancas();

        logFinancas.setId(UUID.randomUUID());
        logFinancas.setDataHora(Instant.now());

        logFinancas.setIdConta(conta.getId());
        logFinancas.setNomeConta(conta.getNome());
        logFinancas.setDataConta(conta.getData());
        logFinancas.setValorConta(conta.getValor().doubleValue());
        logFinancas.setMovimentacao(conta.getMovimentacao().name());

        switch (operacao) {
            case CADASTRO:
                logFinancas.setDescricao("Cadastro de conta");
                break;
            case ALTERACAO:
                logFinancas.setDescricao("Alteração de conta");
                break;
            case EXCLUSAO:
                logFinancas.setDescricao("Exclusão de conta");
                break;
        }

        logFinancasRepository.save(logFinancas);


    }
    // Enum para definir o tipo de operacao do log
    public enum Operacao{
        CADASTRO,
        ALTERACAO,
        EXCLUSAO
    }
}
