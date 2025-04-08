package br.com.cotiinformatica.domain.collections;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log_financas")
@Data
public class LogFinancas {
    private UUID id;
    private Instant dataHora;
    private String descricao;
    private Integer idConta;
    private String nomeConta;
    private Date dataConta;
    private Double valorConta;
    private String movimentacao;
}


