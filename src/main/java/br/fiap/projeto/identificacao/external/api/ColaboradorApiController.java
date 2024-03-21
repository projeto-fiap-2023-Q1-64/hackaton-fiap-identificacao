package br.fiap.projeto.identificacao.external.api;

import br.fiap.projeto.identificacao.adapter.controller.port.IColaboradorRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.ColaboradorRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.ColaboradorResponseDTO;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@RequiredArgsConstructor
@Log4j2
@Api(tags = {"Identificação"})
public class ColaboradorApiController {

    private final IColaboradorRestAdapterController colaboradorAdapterController;

    @PostMapping
    @ApiOperation(value = "Insere as informações do colaborador", notes = "Este endpoint insere as informações de um colaborador que optou por se identificar")
    public ResponseEntity<ColaboradorResponseDTO> insere(@RequestBody ColaboradorRequestDTO colaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorAdapterController.insere(colaborador));
    }

    @PutMapping("/{codigo}")
    @ApiOperation(value = "Atualiza as informações de colaborador", notes = "Este endpoint atualiza as informações de um colaborador")
    public ColaboradorResponseDTO atualiza(@PathVariable String codigo, @RequestBody ColaboradorRequestDTO colaborador) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        return colaboradorAdapterController.atualiza(codigo, colaborador);
    }

    @DeleteMapping("/{codigo}")
    @ApiOperation(value = "Remove o registro de colaborador", notes = "Este endpoint remove o registro de um colaborador")
    public ResponseEntity<Void> remove(@PathVariable String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        colaboradorAdapterController.remove(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigo}")
    @ApiOperation(value = "Busca as informações de colaborador", notes = "Este endpoint busca as informações de um colaborador identificado")
    public ColaboradorResponseDTO busca(@PathVariable String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        return colaboradorAdapterController.busca(codigo);
    }

    @GetMapping("/todos")
    @ApiOperation(value = "Busca todos os colaboradores", notes = "Este endpoint busca todos os colaboradores")
    public List<ColaboradorResponseDTO> buscaTodos() {
        return colaboradorAdapterController.buscaTodos();
    }

    @GetMapping("/matricula")
    @ApiOperation(value = "Busca colaborador por matricula", notes = "Este endpoint busca as informações de um colaborador por matricula")
    public ColaboradorResponseDTO buscaPorCpf(@RequestParam String matricula) throws EntidadeNaoEncontradaException {
        return colaboradorAdapterController.buscaPorMatricula(matricula);
    }
}
