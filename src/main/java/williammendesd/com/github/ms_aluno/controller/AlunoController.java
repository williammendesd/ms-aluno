package williammendesd.com.github.ms_aluno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import williammendesd.com.github.ms_aluno.DTO.AlunoDTO;
import williammendesd.com.github.ms_aluno.model.Aluno;
import williammendesd.com.github.ms_aluno.services.AlunoService;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll(){
        List<AlunoDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id){
        AlunoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }


}
