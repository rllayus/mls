package com.laredo.service.client.impl;

import com.laredo.dto.AuthenticationDto;
import com.laredo.dto.OKAuthDto;
import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.service.client.ClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public OKAuthDto login(String url, int connectTimeout, int readTimeout, AuthenticationDto okAuthDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String urlCompleto = url + "/api/v1/authUser/login";
        System.out.println(urlCompleto);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory(connectTimeout, readTimeout));
        ResponseEntity<OKAuthDto> response = restTemplate
                .postForEntity(urlCompleto,
                        new HttpEntity<>(okAuthDto, headers), OKAuthDto.class);

        return response.getBody();
    }

    @Override
    public TransferResponseDto transfer(String url, int connectTimeout, int readTimeout, String user, String password, TransferRequestDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String jwt;
        try {
            AuthenticationDto authenticationDto = new AuthenticationDto();
            authenticationDto.setUsername(user);
            authenticationDto.setPassword(password);
            jwt = login(url, connectTimeout, readTimeout, authenticationDto).getToken();
        } catch (Exception e) {
            throw e;
        }

        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory(connectTimeout, readTimeout));
        ResponseEntity<TransferResponseDto> response = restTemplate
                .postForEntity(url + "/v1/api/transferencia",
                        new HttpEntity<>(dto, headers), TransferResponseDto.class);
        return response.getBody();
    }


    private SimpleClientHttpRequestFactory getClientHttpRequestFactory(int connectTimeout, int readTimeout) {
        disableSslVerification();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        return requestFactory;
    }

    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
