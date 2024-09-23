package williammendesd.com.github.ms_aluno.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import williammendesd.com.github.ms_aluno.DTO.AlunoDTO;
import williammendesd.com.github.ms_aluno.services.AlunoService;
import williammendesd.com.github.ms_aluno.services.exceptions.ResourceNotFoundException;
import williammendesd.com.github.ms_aluno.tests.Factory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService service;
    private AlunoDTO alunoDTO;
    private Long existingId;
    private Long nonExistingId;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 10L;

        alunoDTO = Factory.createAlunoDTO();

        List<AlunoDTO> list = List.of(alunoDTO);

        when(service.findAll()).thenReturn(list);
        when(service.findById(existingId)).thenReturn(alunoDTO);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        when(service.insert(any())).thenReturn(alunoDTO);
        when(service.update(eq(existingId), any())).thenReturn(alunoDTO);
        when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
        doNothing().when(service).delete(existingId);
        doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
    }

    @Test
    public void findAllShouldReturnListPagamentoDTO() throws Exception {

        //chamando uma requisição com o método get em /pagamentos
        ResultActions result = mockMvc.perform(get("/alunos")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }



}
