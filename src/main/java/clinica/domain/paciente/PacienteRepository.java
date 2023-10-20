package clinica.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Paciente
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}