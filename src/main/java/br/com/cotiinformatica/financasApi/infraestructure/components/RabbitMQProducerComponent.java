package br.com.cotiinformatica.financasApi.infraestructure.components;

import br.com.cotiinformatica.financasApi.domain.models.entities.Conta;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class RabbitMQProducerComponent {
    @Autowired RabbitTemplate rabbitTemplate;
    @Autowired ObjectMapper objectMapper;
    @Autowired Queue queue;

    /*
     * Método para gravar os dados da conta
     * na fila do servidor de mensageria
     */
    public void sendMessage(Conta conta) {
        try {

            //serializar os dados da conta para formato JSON
            String json = objectMapper.writeValueAsString(conta);

            //enviando a mensagem para a fila
            rabbitTemplate.convertAndSend(queue.getName(), json);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}


