package williammendesd.com.github.ms_aluno.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import williammendesd.com.github.ms_aluno.model.Aluno;
import williammendesd.com.github.ms_aluno.model.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class AlunoDTO {
    private Long id;

    @NotBlank(message = "Campo requirido")
    private String nome;

    @NotBlank(message = "Campo requirido")
    private String email;

    @NotBlank(message = "Campo requirido")
    private String password;

    @NotBlank(message = "Campo requirido")
    private String rm;

    @NotNull(message = "Campo requirido")
    private Status status;

    @NotBlank(message = "Campo requirido")
    private String turma;

    public AlunoDTO(Aluno entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        password = entity.getPassword();
        rm = entity.getRm();
        status = entity.getStatus();
        turma = entity.getTurma();
    }
}
