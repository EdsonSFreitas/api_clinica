package clinica.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import clinica.domain.endereco.Endereco;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * Entidade Paciente
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter //Lombok irá gerar os getters
@NoArgsConstructor //Lombok irá criar um construtor sem argumento
@AllArgsConstructor //Lombok irá criar um construtor com todos os argumentos
//@EqualsAndHashCode(of = "id") //Lombo ira gerar o hashCode apenas do id mas não é recomendado por questão de performance
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Valid
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Paciente paciente = (Paciente) o;
        return getId() != null && Objects.equals(getId(), paciente.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void atualizaInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.telefone() != null) this.telefone = dados.telefone();
        if (dados.endereco() != null) this.endereco.atualizarInformacoes(dados.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}