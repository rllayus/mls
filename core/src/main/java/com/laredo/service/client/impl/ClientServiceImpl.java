package com.laredo.service.client.impl;

import com.laredo.dto.AuthenticationDto;
import com.laredo.dto.OKAuthDto;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.service.client.ClientService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public OKAuthDto login(AuthenticationDto okAuthDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate =new RestTemplate();
        return restTemplate.postForEntity("http://172.16.41.29:8081/api/v1/authUser/login",new HttpEntity<>(okAuthDto, headers), OKAuthDto.class).getBody();
    }

    @Override
    public TransferResponseDto transfer(String jwt, TransferRequestDto dto) {
        return null;
    }
}
