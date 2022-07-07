package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    HttpHeaders headers =  new HttpHeaders();
    


    public List<User> showAllUsers() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, entity,
                        new ParameterizedTypeReference<List<User>>() {});
        headers.add("Cookie", String.valueOf(responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE)));
        List<User> allUsers = responseEntity.getBody();
        return allUsers;
    }


    public void saveUser (User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, entity, String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

    }

    public void updateUser(User user) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

    }

    public void delete(User user, Long id) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());


    }



    public String createProducts(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);

        return restTemplate.exchange("http://94.198.50.185:7081/api/users",
                HttpMethod.POST, entity, String.class).getBody();
    }


    public String updateProduct(@PathVariable("id") String id, @RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);

        return restTemplate.exchange(
                "http://94.198.50.185:7081/api/users/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }
}