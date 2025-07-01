package com.msqrtets.controller;

import com.msqrtets.service.QrGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class QrController {

    private final QrGenerateService service;

    @GetMapping("/qr")
    public String qr() {
        return service.generateQRCode("https://oxu.az/", "myqr.png");
    }
}
