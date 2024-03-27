package com.cesi.cda.courspoo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalAPIBusiness {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalAPIBusiness(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchDataFromExternalAPI() {
        // Exemple de requête GET à une API externe
        String apiUrl = "https://8443-cesi2022-spring3-nk8oef9q6ta.ws-eu110.gitpod.io/api/v1/persons";
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
