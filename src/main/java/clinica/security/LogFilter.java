package clinica.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Classe opcional para fins de estudos, pode ser removida sem impactar no projeto
 * @author Edson da Silva Freitas
 * {@code @created} 22/05/2023
 * {@code @project} api
 */
@WebFilter(urlPatterns = "/api/**")
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Requisição recebida em: " + LocalDateTime.now());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}