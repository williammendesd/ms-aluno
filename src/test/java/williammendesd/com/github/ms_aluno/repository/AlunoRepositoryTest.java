package williammendesd.com.github.ms_aluno.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import williammendesd.com.github.ms_aluno.model.Aluno;

import java.util.Optional;

@DataJpaTest
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private int countTotalPagamento;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 33L;
        //verificar quanto registro tem no seed
        countTotalPagamento = 3;
    }

    @Test
    @DisplayName("Deveria excluir aluno quando Id existe")
    public void deleteShouldDeletObjectWhenIdExists() {
        // Act
        repository.deleteById(existingId);
        // Assert
        Optional<Aluno> result = repository.findById(existingId);
        //testa se existe um obj dentro do Optional
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("findById Deveria retornar um Optional não vazio quando o Id existe")
    public void findByIdShoulReturnNonEmptyOptionalWhenExistsId(){

        Optional<Aluno> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("findById Deveria retornar um Optional vazio quando o Id não existe")
    public void findByIdShoulReturnEmptyOptionalWhenIdDoesNotExists(){

        Optional<Aluno> result = repository.findById(nonExistingId);
        Assertions.assertFalse(result.isPresent());
        // ou
        Assertions.assertTrue(result.isEmpty());
    }

}
