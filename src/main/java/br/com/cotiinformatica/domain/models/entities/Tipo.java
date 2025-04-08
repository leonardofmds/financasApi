package br.com.cotiinformatica.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
