package williammendesd.com.github.ms_aluno.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import williammendesd.com.github.ms_aluno.DTO.AlunoDTO;
import williammendesd.com.github.ms_aluno.model.Aluno;
import williammendesd.com.github.ms_aluno.model.Status;
import williammendesd.com.github.ms_aluno.repository.AlunoRepository;
import williammendesd.com.github.ms_aluno.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    @Transactional
    public List<AlunoDTO> findAll(){
        List<Aluno> list = repository.findAll();
        return list.stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public AlunoDTO findById(Long id){
        Aluno aluno = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso nao encontrado"));
        return new AlunoDTO(aluno);
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO dto){
        Aluno entity = new Aluno();
        copyDtoToEntity(dto, entity);
        entity.setStatus(Status.MATRICULADO);
        repository.save(entity);
        return new AlunoDTO(entity);
    }

    @Transactional
    public AlunoDTO update(Long id, AlunoDTO dto){
        try {
            Aluno entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            repository.save(entity);
            return new AlunoDTO(entity);
        }catch (Exception e){
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso nao encontraod");
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new ResourceNotFoundException("Falha ao deletar");
        }

    }

    private void copyDtoToEntity(AlunoDTO dto,Aluno entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setRm(dto.getRm());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setTurma(dto.getTurma());
    }

}
