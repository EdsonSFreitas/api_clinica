package clinica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Classe para configuração de segurança e definir que somente /login esta acessivel sem autenticao
 *
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
@Configuration // Indica para o Spring que é uma classe de configuração
@EnableWebSecurity // Indica que vamos personalizar as configurações de segurança
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter; //Instanciando para definir a ordem dos filter, por padrão é a do Spring mas precisamos alterar

    @Bean // Devolve um objeto para o Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                .anyRequest().authenticated() //Qualquer outra requisicao precisa estar logado
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)//Primeiro o nosso filtro, depois o do Spring
                .build();
        //csrf pra desativar proteção contra ataque Cross-Site Request Forgery, pq o Token já protege contra ele
    }

    /* // Na versão Spring antes da 3.0.0 era usado .antMatchers ao inves do novo .requestMatchers
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and().build();
}
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
        //Método necessário para o Spring conseguir carregar o AuthenticationManager da classe AuthenticacaoController.java
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //Configurando Spring para trabalhar com algoritimo de hashing Bcrypt
    }

}