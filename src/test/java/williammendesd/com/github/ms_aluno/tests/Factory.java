package williammendesd.com.github.ms_aluno.tests;

import williammendesd.com.github.ms_aluno.DTO.AlunoDTO;
import williammendesd.com.github.ms_aluno.model.Aluno;
import williammendesd.com.github.ms_aluno.model.Status;

import java.math.BigDecimal;

public class Factory {

    public static Aluno createAluno(){
        Aluno aluno = new Aluno(9L,"Nome", "email","password","rm", Status.TRANCADO, "turma");
        return aluno;
    }

    public static AlunoDTO createAlunoDTO(){
        Aluno aluno = createAluno();
        return new AlunoDTO(aluno);
    }

    public static AlunoDTO createNewAlunoDTO(){
        Aluno aluno = createAluno();
        aluno.setId(null);
        return new AlunoDTO(aluno);
    }

}
