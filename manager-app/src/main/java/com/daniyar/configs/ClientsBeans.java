package com.daniyar.configs;


import com.daniyar.restClient.RestClientProductRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientsBeans {

    /*
    here we call the class not the interface
    but the injection comes through interfaces
     */
    @Bean
    public RestClientProductRestClientImpl ProductRestClient(
            @Value("${selmag.services.catalogue.uri:http://localhost:8080}") String CatalogueBaseUri) {
        return new RestClientProductRestClientImpl(RestClient.builder()
                .baseUrl(CatalogueBaseUri)
                .build());
    }

}
