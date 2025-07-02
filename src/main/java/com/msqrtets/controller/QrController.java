package com.msqrtets.controller;

import com.msqrtets.service.test.QrGenerateService;
import com.msqrtets.service.test.RecaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class QrController {

    private final QrGenerateService qrService;
    private final RecaptchaService recaptchaService;

    @GetMapping("/qr/{name}")
    public String qr(@PathVariable String name, @RequestParam("recaptchaToken") String recaptchaToken) {
        boolean valid = recaptchaService.verify(recaptchaToken);
        if (!valid) {
            return "Captcha yoxlanışı uğursuz oldu.";
        }
        return qrService.generateQRCode(name);
    }
}
