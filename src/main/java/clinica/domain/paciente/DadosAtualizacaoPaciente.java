package clinica.domain.paciente;

import jakarta.validation.constraints.NotNull;
import clinica.domain.endereco.DadosEndereco;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 19/05/2023
 * {@code @project} api
 */
public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}