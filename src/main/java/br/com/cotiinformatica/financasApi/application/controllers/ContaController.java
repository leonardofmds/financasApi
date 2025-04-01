package br.com.cotiinformatica.financasApi.application.controllers;

import br.com.cotiinformatica.financasApi.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.financasApi.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.financasApi.domain.services.interfaces.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaController {

    @Autowired ContaService contaService;

    @PostMapping("criar")
    public ContaResponseDto criar(ContaRequestDto request) throws Exception {
        return contaService.cadastrar(request);
    }

    @PutMapping("atualizar/{id}")
    public ContaResponseDto atualizar(Integer id, @RequestBody @Valid ContaRequestDto request) throws Exception {
        return contaService.atualizar(id, request);
    }

    @DeleteMapping("excluir/{id}")
    public ContaResponseDto excluir(@PathVariable Integer id) throws Exception {
        return contaService.excluir(id);
    }

    @GetMapping("consultar")
    public List<ContaResponseDto> getAll() throws Exception {
        return contaService.consultarTodos();
    }

    @GetMapping("obter/{id}")
    public ContaResponseDto consultarPorId(@PathVariable Integer id) throws Exception {
        return contaService.consultarPorId(id);
    }

}
