package williammendesd.com.github.ms_aluno.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import williammendesd.com.github.ms_aluno.DTO.AlunoDTO;
import williammendesd.com.github.ms_aluno.model.Aluno;
import williammendesd.com.github.ms_aluno.repository.AlunoRepository;
import williammendesd.com.github.ms_aluno.services.AlunoService;
import williammendesd.com.github.ms_aluno.services.exceptions.ResourceNotFoundException;
import williammendesd.com.github.ms_aluno.tests.Factory;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService service;

    @Mock
    private AlunoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private Aluno aluno;
    private AlunoDTO alunoDTO;

    @BeforeEach
    void setup() throws Exception {
        existingId = 9L;
        nonExistingId = 10L;
        //precisa simular o comportamento do objeto mockado;
        //delete - quando id existe;
        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        //delete - quando id não existe
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
        //delete - primeiro caso - deleta
        // não faça nada (void) quando ...
        Mockito.doNothing().when(repository).deleteById(existingId);
        //próximos testes
        aluno = Factory.createAluno();
        alunoDTO = new AlunoDTO(aluno);
        //simulação do comportamento
        // findById
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(aluno));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
        // insert
        Mockito.when(repository.save(any())).thenReturn(aluno);
        //update -primeiro caso - id existe
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(aluno);
        //update -segundo caso - id não existe
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("delete Deveria não fazer nada quando Id existe")
    public void deleteShouldDoNothingWhenIdExists() {
        // no service, delete é do tipo void
        Assertions.assertDoesNotThrow(
                () -> {
                    service.delete(existingId);
                }
        );
    }

    @Test
    @DisplayName("delete Deveria lançar exceção ResourceNotFoundException quando Id não existe")
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }

    @Test
    public void findByIdShouldReturnAlunoDTOWhenIdExists(){
        AlunoDTO result = service.findById(existingId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
        Assertions.assertEquals(result.getRm(), aluno.getRm());
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void insertShouldReturnAlunoDTO(){

        AlunoDTO result = service.insert(alunoDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), aluno.getId());
    }

    @Test
    public void updateShouldReturAlunoDTOWhenIdExists(){

        AlunoDTO result = service.update(aluno.getId(), alunoDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
        Assertions.assertEquals(result.getRm(), aluno.getRm());
    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, alunoDTO);
        });
    }
}
