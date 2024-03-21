package br.fiap.projeto.identificacao.adapter.controller.rest.exception;

import br.fiap.projeto.identificacao.external.api.ColaboradorApiController;
import br.fiap.projeto.identificacao.usecase.exception.BaseException;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ColaboradorApiController.class)
@Log4j2
public class ColaboradorErrorHandler {

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<MensagemErroDTO> entidadeNaoEncontradaHandler(EntidadeNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler({EntradaInvalidaException.class})
    public ResponseEntity<MensagemErroDTO> entradaInvalidaHandler(EntradaInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<MensagemErroDTO> genericHandler(EntradaInvalidaException ex) {
        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroDTO(BaseException.DEFAULT_CODE, ex.getMessage()));
        log.error("Exceção não tratada: ", ex);
        return erro;
    }
}
