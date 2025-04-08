package br.com.cotiinformatica.infrastructure.repositories;

import br.com.cotiinformatica.domain.models.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
