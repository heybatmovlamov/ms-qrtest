package com.msqrtets.service.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QrGenerateService {

    private final Map<String, String> tokenSecretMap = new HashMap<>();
    private final Map<String, String> urlSecretMap = new HashMap<>();

    public String generateQrCode(String secret) {
        String token = generateTokenAndSave(secret);
        String url = "http://localhost:8080/v1/test/qr/verify?token=" + token;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);

            Path path = FileSystems.getDefault().getPath("qr-" + token + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return url;

        } catch (WriterException | IOException e) {
            log.error("QR kod yaradılarkən xəta baş verdi", e);
            return null;
        }
    }

    public void validateToken(String token, String secret) {
        String correctSecret = tokenSecretMap.get(token);
        if (correctSecret == null || !correctSecret.equals(secret)) {
            throw new RuntimeException();
        }
        log.info("User valid !");
    }

    private String generateTokenAndSave(String secret) {
        String token = UUID.randomUUID().toString();
        tokenSecretMap.put(token, secret);
        return token;
    }

}
