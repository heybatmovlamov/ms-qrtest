package com.msqrtets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecaptchaService {

    @Value("${google.recaptcha.secret}")
    private String secret;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean verify(String token) {
        String url = VERIFY_URL + "?secret=" + secret + "&response=" + token;
        Map response = restTemplate.postForObject(url, null, Map.class);

        if (response == null) return false;
        Object success = response.get("success");
        return success != null && (Boolean) success;
    }
}
