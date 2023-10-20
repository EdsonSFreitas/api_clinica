package clinica.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}