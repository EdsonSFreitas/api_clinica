package clinica.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import clinica.domain.endereco.DadosEndereco;

/**
 * Classe tipo record foi introduzida no Java 14. Ela é imutavel e implementa automaticamente os getters,equals, hashcode e toString;
 * Essa classe usa o padrão DTO - Data Transfer Object, usado para representar os dados em uma API
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
public record DadosCadastroMedico(
        //Verifica se é nulo ou vazio e é só para campos String
        @NotBlank(message = "{nome.obrigatorio}") //Formato usando arquivo ValidationMessages.properties
        String nome,
        @NotBlank(message = "Email é obrigatório (Mensagem direta no campo de validação)") //Formato sem usar o arquivo ValidationMessages.properties
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}") //Valida se campo tem apenas numeros, minimo 4 e maximo 6 numeros e usa ValidationMessages.properties
        String crm,
        @NotNull(message = "{especialidade.obrigatoria}") //Como esse campo não é String não posso usar @NotBlank
        Especialidade especialidade,
        @NotNull(message = "{endereco.obrigatorio}") //Como esse campo não é String não posso usar @NotBlank
        @Valid //Devido ao DadosEndereco ser outro record DTO, valide também os objeto que estão dentro dele
        DadosEndereco endereco

        /*
        Para alterar o texto exibido para cada validação
        1. Posso adicionar a mensagem diretamente como fiz no campo email (message = "Email é obrigatório")
        2. Dentro de resources criar o arquivo ValidationMessages.properties e adicionar todas as mensagens:
        email.invalido=Formato do email é inválido
        crm.obrigatorio=CRM é obrigatório
        crm.invalido=Formato do CRM é inválido
         */
) {
}