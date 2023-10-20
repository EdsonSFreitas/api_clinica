package clinica.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import clinica.domain.endereco.Endereco;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
@Entity(name = "Medico")
@Table(name = "medicos")
@Getter //Lombok irá gerar os getters
@NoArgsConstructor //Lombok irá criar um construtor sem argumento
@AllArgsConstructor //Lombok irá criar um construtor com todos os argumentos
//@EqualsAndHashCode(of = "id") //Lombo ira gerar o hashCode apenas do id mas não é recomendado por questão de performance
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Valid
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medico medico = (Medico) o;
        return getId() != null && Objects.equals(getId(), medico.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.telefone() != null) this.telefone = dados.telefone();
        if (dados.endereco() != null) this.endereco.atualizarInformacoes(dados.endereco());

    }

    public void excluir() {
        this.ativo = false;
    }
}