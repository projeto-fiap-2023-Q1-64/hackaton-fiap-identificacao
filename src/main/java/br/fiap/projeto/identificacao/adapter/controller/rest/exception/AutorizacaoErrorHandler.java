package br.fiap.projeto.identificacao.adapter.controller.rest.exception;

import br.fiap.projeto.identificacao.external.api.LoginApiController;
import br.fiap.projeto.identificacao.usecase.exception.BaseException;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice(basePackageClasses = LoginApiController.class)
public class AutorizacaoErrorHandler {

    @ExceptionHandler({LoginInvalidoException.class})
    public ResponseEntity<MensagemErroDTO> loginInvalido(LoginInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<MensagemErroDTO> erroInesperado(Exception ex) {
       ResponseEntity erro = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroDTO(BaseException.DEFAULT_CODE, "Ocorreu um erro inesperado!"));
       log.error("Exceção não tratada: ", ex);
       return erro;
    }
}
