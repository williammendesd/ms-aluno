package williammendesd.com.github.ms_aluno.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import williammendesd.com.github.ms_aluno.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
