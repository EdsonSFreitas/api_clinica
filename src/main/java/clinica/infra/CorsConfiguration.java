package clinica.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configurar o CORS e habilitar uma origem específica para consumir a API (Para fins de estudos, nao ha um servico consumindo essa API)
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
        /*
        http://localhost:3000 seria o endereço da aplicação Front-end e .allowedMethods os métodos que serão permitidos para serem executados.
        Com isso, você poderá consumir a sua API sem problemas a partir de uma aplicação Front-end.
        Se usar a anotação @CrossOrigin(origins="", allowedHeaders = "") precisaria utilizar em todos os controllers.
        Usando a classe de configuração fica global, não precisarei adicionar anotação nos controllers.
         */

    }
}