package br.com.cotiinformatica.financasApi.infraestructure.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue queue() {
        return new Queue("apifinancas_contas", true);
    }
}
