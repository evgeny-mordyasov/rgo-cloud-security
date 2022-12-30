package rgo.cloud.security.config.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.reactive.function.client.WebClient;
import rgo.cloud.common.api.exception.EntityNotFoundException;
import rgo.cloud.security.config.domain.ClientDetails;
import rgo.cloud.security.config.util.Endpoint;

public class ClientDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String mail) {
        return findByMail(mail);
    }

    private ClientDetails findByMail(String mail) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://auth-app:8090" + Endpoint.Client.BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ClientDetails response = webClient.get()
                .uri("?mail=" + mail)
                .retrieve()
                .bodyToMono(ClientDetails.class)
                .block();

        if (response.getObject() == null) {
            throw new EntityNotFoundException("Client by mail='" + mail + "' not found.");
        }

        return response;
    }
}
