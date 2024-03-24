package br.fiap.projeto.identificacao.external.api;

import br.fiap.projeto.identificacao.adapter.controller.port.IAutorizaAcessoRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.rest.request.LoginRequestDTO;
import br.fiap.projeto.identificacao.adapter.controller.rest.response.LoginResponseDTO;
import br.fiap.projeto.identificacao.usecase.exception.LoginInvalidoException;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags = {"Login"})
public class LoginApiController {
    private IAutorizaAcessoRestAdapterController autorizaAcessoRestAdapterController;

    public LoginApiController(IAutorizaAcessoRestAdapterController autorizaAcessoRestAdapterController) {
        this.autorizaAcessoRestAdapterController = autorizaAcessoRestAdapterController;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> validaAutorizacao(@RequestBody LoginRequestDTO usuarioLogin) throws LoginInvalidoException {
        return ResponseEntity.ok().body(autorizaAcessoRestAdapterController.validaLogin(usuarioLogin));
    }

    @GetMapping("/printaSenha")
    public ResponseEntity<String> printaSenha(@RequestBody LoginRequestDTO usuarioLogin) {

        return ResponseEntity.ok().body(autorizaAcessoRestAdapterController.printaSenha(usuarioLogin.getMatricula()));
    }
}
