package com.laredo.service.client.impl;

import com.laredo.dto.AuthenticationDto;
import com.laredo.dto.OKAuthDto;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.service.client.ClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientServiceImpl implements ClientService {

    @PostConstruct
    public void init(){
        System.out.println("Inicio!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    @Override
    public OKAuthDto login(AuthenticationDto okAuthDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<OKAuthDto> response = restTemplate
                .postForEntity("http://172.16.41.29:8081/api/v1/authUser/login",
                        new HttpEntity<>(okAuthDto, headers), OKAuthDto.class);
        if(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){

        }
        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        throw new Exception("Error");
    }

    @Override
    public TransferResponseDto transfer(String jwt, TransferRequestDto dto) {
        return null;
    }
}
