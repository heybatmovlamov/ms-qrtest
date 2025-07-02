package com.msqrtets.controller;

import com.msqrtets.service.test.QrGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test/qr")
public class QrController {

    private final QrGenerateService service;

    @PostMapping("/generate")
    public ResponseEntity<String> generateQr(@RequestParam String secret) {
        return ResponseEntity.ok(service.generateQrCode(secret));
    }

    @PostMapping("/validate")
    public ResponseEntity<Void> validateQr(@RequestParam String token, @RequestParam String secret) {
        service.validateToken(token, secret);
        return ResponseEntity.ok().build();
    }
}
