package br.com.cotiinformatica.financasApi.infraestructure.repositories;

import br.com.cotiinformatica.financasApi.domain.models.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
}
