package br.fiap.projeto.identificacao.external.config;

import br.fiap.projeto.identificacao.adapter.controller.ColaboradorRestAdapterController;
import br.fiap.projeto.identificacao.adapter.controller.port.IColaboradorRestAdapterController;
import br.fiap.projeto.identificacao.adapter.gateway.ColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.external.repository.postgres.SpringColaboradorRepository;
import br.fiap.projeto.identificacao.usecase.GestaoColaboradorUseCase;
import br.fiap.projeto.identificacao.usecase.port.IColaboradorRepositoryAdapterGateway;
import br.fiap.projeto.identificacao.usecase.port.IGestaoColaboradorUsecase;
import br.fiap.projeto.identificacao.usecase.port.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeansConfiguration {

    private PasswordEncoder passwordEncoder = (s) -> new BCryptPasswordEncoder().encode(s);

    @Bean
    public IGestaoColaboradorUsecase gestaoColaboradorUsecase(IColaboradorRepositoryAdapterGateway colaboradorRepository) {
        return new GestaoColaboradorUseCase(colaboradorRepository, passwordEncoder);
    }

    @Bean
    public IColaboradorRestAdapterController colaboradorRestAdapterController(IGestaoColaboradorUsecase gestaoColaboradorUsecase) {
        return new ColaboradorRestAdapterController(gestaoColaboradorUsecase);
    }

    @Bean
    public IColaboradorRepositoryAdapterGateway colaboradorRepositoryAdapterGateway(SpringColaboradorRepository springColaboradorRepository) {
        return new ColaboradorRepositoryAdapterGateway(springColaboradorRepository);
    }
}
