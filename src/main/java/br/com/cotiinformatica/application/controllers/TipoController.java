package br.com.cotiinformatica.application.controllers;

import br.com.cotiinformatica.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.models.dtos.TipoResponseDto;
import br.com.cotiinformatica.domain.services.interfaces.TipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo")
public class TipoController {

    @Autowired TipoService tipoService;

    @PostMapping("criar")
    public TipoResponseDto criar(@RequestBody @Valid TipoRequestDto request) throws Exception{
        return tipoService.cadastrar(request);
    }

    @PutMapping("atualizar/{id}")
    public TipoResponseDto atualizar(@PathVariable Integer id, @RequestBody @Valid TipoRequestDto request) throws Exception{
        return tipoService.atualizar(id, request);
    }

    @DeleteMapping("excluir/{id}")
    public TipoResponseDto excluir(@PathVariable Integer id) throws Exception {
        return tipoService.excluir(id);
    }

    @GetMapping("consultar")
    public List<TipoResponseDto> getAll() throws Exception {
        return tipoService.consultarTodos();
    }
    @GetMapping("obter/{id}")
    public TipoResponseDto getById(@PathVariable Integer id) throws Exception {
        return tipoService.consultarPorId(id);
    }

}
