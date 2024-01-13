 package br.com.mike.comum.service;

import br.com.mike.comum.records.AutenticacaoRecord;
import br.com.mike.comum.records.TokenRecord;
import br.com.mike.comum.serviceInterface.IAutenticationService;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class AutenticationService{

    private IAutenticationService iAutenticationService;

    public AutenticationService(String url){
        iAutenticationService = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(IAutenticationService.class);
    }

    private RestTemplate restTemplate;
    private static String CAMINHO;
    public AutenticationService(String url, HttpHeaders sec) throws Exception{
        CAMINHO = url + "/seguranca";
        restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(new Inteceptor(sec)));
    }


    public AutenticacaoRecord validarToken() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        var value = restTemplate.exchange(CAMINHO + "/validarToken", HttpMethod.GET, requestEntity, AutenticacaoRecord.class);
        return value.getBody();
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
