package clinica.domain.medico;

import clinica.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para atualizar cadastro medico
 * @author Edson da Silva Freitas
 * {@code @created} 19/05/2023
 * {@code @project} api
 */
public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
    //Usando DTO de endereco pq todos os campos podem ser atualizados
    //Se existisse alguma restricao eu deveria criar um novo DTO de endereco especifico para esse DTO
}