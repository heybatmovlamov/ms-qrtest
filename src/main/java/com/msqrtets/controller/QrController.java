package com.msqrtets.controller;

import com.msqrtets.service.QrGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class QrController {

    private final QrGenerateService service;

    @GetMapping("/qr/{name}")
    public String qr(@PathVariable String name) {
        return service.generateQRCode(name);
    }
}
