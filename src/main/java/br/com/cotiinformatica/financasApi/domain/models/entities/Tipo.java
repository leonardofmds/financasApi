package br.com.cotiinformatica.financasApi.domain.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "conta_tipo",
            joinColumns = @JoinColumn(name = "id_tipo"),
            inverseJoinColumns = @JoinColumn(name = "id_conta"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_tipo", "id_conta"})
    )
    private List<Conta> contas;

}
