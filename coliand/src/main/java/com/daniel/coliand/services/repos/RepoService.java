package com.daniel.coliand.services.repos;

import com.daniel.coliand.models.repos.NewRepo;
import com.daniel.coliand.models.repos.Repo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RepoService {
    private final RestTemplate rst;
    private final String token = "";


    public RepoService(RestTemplateBuilder rstBlder) {

        this.rst = rstBlder.build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        this.rst.setRequestFactory(requestFactory);
        //this.rst.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public List<Repo> getUserRepos(){
        String url = "https://api.github.com/user/repos";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        httpHeaders.add("Authorization", "Bearer " + token); //If it fails, maybe it is because of that empty space after Bearer

        //Building Request.
        HttpEntity request = new HttpEntity(httpHeaders);

        //Configuring Jackson2MessageConverter.
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
        messageConverters.add(map);
        messageConverters.add(new FormHttpMessageConverter());
        this.rst.setMessageConverters(messageConverters);

        ResponseEntity<List<Repo>> response = this.rst.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Repo>>() {});
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        else {
            return null;
        }
    }

   public Repo addNewRepo(String name, boolean autoInit, boolean privateRepository, String gitignoreTemplate) {
        String url = "https://api.github.com/user/repos";
        NewRepo new_repo = new NewRepo(name,autoInit,privateRepository,gitignoreTemplate);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        httpHeaders.add("Authorization", "Bearer " + token); //If it fails, maybe it is because of that empty space after Bearer

        //Building Request.
        HttpEntity request = new HttpEntity(httpHeaders);

        //Merging the object we want to send and the headers.
        HttpEntity<NewRepo> entity = new HttpEntity<>(new_repo, httpHeaders);
        ResponseEntity<Repo> response = this.rst.postForEntity(url, entity, Repo.class);

       // check response status code
       if (response.getStatusCode() == HttpStatus.CREATED) {
           return response.getBody();
       } else {
           return null;
       }
    }

    public Repo patchRepo(String repoName, String username,NewRepo patchRepo) {
        String url = String.format("https://api.github.com/repos/%s/%s", username,repoName );
        System.out.println(patchRepo.name);
        System.out.println(repoName);
        System.out.println(url);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        httpHeaders.add("Authorization", "Bearer " + token); //If it fails, maybe it is because of that empty space after Bearer
       // httpHeaders.add("X-HTTP-Method-Override","PATCH");

        //Building Request.
        HttpEntity request = new HttpEntity(httpHeaders);
        HttpEntity<NewRepo> entity = new HttpEntity<>(patchRepo, httpHeaders);

        ResponseEntity<Repo> response = this.rst.exchange(url, HttpMethod.PATCH,entity, Repo.class);


        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
}
