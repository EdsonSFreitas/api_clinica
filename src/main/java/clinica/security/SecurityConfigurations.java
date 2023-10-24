package clinica.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;


/**
 * Classe para configuração de segurança e definir que somente /login esta acessivel sem autenticao
 *
 * @author Edson da Silva Freitas
 * {@code @created} 21/05/2023
 * {@code @project} api
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfigurations {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Bean
    public SecurityFilter filter(){
        return new SecurityFilter(exceptionResolver);
    }

    String[] unSecuredPaths = new String[]{
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    private AntPathRequestMatcher[] getAntPathRequestMatchers() {
        AntPathRequestMatcher[] requestMatchers = new AntPathRequestMatcher[unSecuredPaths.length];
        for (int i = 0; i < unSecuredPaths.length; i++) {
            requestMatchers[i] = new AntPathRequestMatcher(unSecuredPaths[i]);
        }
        return requestMatchers;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        AntPathRequestMatcher[] allowUnSecuredPaths = getAntPathRequestMatchers();

        MvcRequestMatcher mvcRequestMatcherLogin = mvc.pattern( HttpMethod.POST,"/login/**");
        MvcRequestMatcher mvcRequestMatcherInsertUsuario = mvc.pattern(HttpMethod.POST, "/usuario/**");
        MvcRequestMatcher mvcRequestMatcherHome = mvc.pattern(HttpMethod.GET, "/home");

        /* Config necessaria para nao precisar desativar o CSRF para o endpoint /api/v1/user
        mas requisicao POST deve conter o TOKEN CSRF - https://www.baeldung.com/postman-send-csrf-token */
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
        delegate.setCsrfRequestAttributeName(null);
        /* Use only the handle() method of XorCsrfTokenRequestAttributeHandler and the
        default implementation of resolveCsrfTokenValue() from CsrfTokenRequestHandler */
        CsrfTokenRequestHandler requestHandler = delegate::handle;

        return http
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(tokenRepository)
                        .csrfTokenRequestHandler(requestHandler)
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
                //.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher(Arrays.toString(allowUnSecuredPaths))))
                .csrf(csrf -> csrf.ignoringRequestMatchers(mvcRequestMatcherLogin))
                //.csrf(csrf -> csrf.ignoringRequestMatchers(allowUnSecuredPaths))
                .csrf(csrf -> csrf.ignoringRequestMatchers(mvcRequestMatcherInsertUsuario))
                .csrf(csrf -> csrf.ignoringRequestMatchers(mvc.pattern(HttpMethod.PUT, "/usuario/**")))
                .csrf(csrf -> csrf.ignoringRequestMatchers(mvc.pattern(HttpMethod.POST, "/login/**")))
                .csrf(csrf -> csrf.ignoringRequestMatchers(mvc.pattern(HttpMethod.PATCH, "/usuario/changestatus/**")))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((mvAauthorize) -> mvAauthorize
                        .requestMatchers(mvcRequestMatcherLogin).permitAll()
                        .requestMatchers(mvcRequestMatcherHome).permitAll()
                )
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(allowUnSecuredPaths).permitAll()
                        .anyRequest().authenticated()
                )
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}