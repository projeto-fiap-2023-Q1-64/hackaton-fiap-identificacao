package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.IGestaoColaboradorUsecase;
import br.fiap.projeto.identificacao.usecase.port.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration @RequiredArgsConstructor
public class PostgresColaboradorDataLoader {

    private final IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    @SneakyThrows
    public void init() {
        List<Colaborador> colaboradors = Collections.singletonList(
                new Colaborador("2a643454-e2e6-4ed4-9f77-52a94ec60642", "Colaborador1", "8999", "pos.fiap.grupo.64@gmail.com", passwordEncoder.encode("Abc1234"))
        );
        colaboradors.forEach(c -> {
            try {
                colaboradorRepositoryAdapterGateway.insere(c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
