 package br.com.mike.comum.service;

import br.com.mike.comum.records.AutenticacaoRecord;
import br.com.mike.comum.records.TokenRecord;
import br.com.mike.comum.serviceInterface.IAutenticationService;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;

import java.net.URI;

public class AutenticationService{

    private IAutenticationService iAutenticationService;

    public AutenticationService(String url){
        iAutenticationService = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(IAutenticationService.class);
    }

    public AutenticacaoRecord validarToken(String token) throws Exception {
        return iAutenticationService.validarToken(token).readEntity(AutenticacaoRecord.class);
    }

    public TokenRecord criarToken(String id, Integer tempoToken) throws Exception {
        return iAutenticationService.criarToken(id, tempoToken).readEntity(TokenRecord.class);
    }

    public TokenRecord atualizarToken(String token) throws Exception {
        return iAutenticationService.atualizarToken(token).readEntity(TokenRecord.class);
    }
}
