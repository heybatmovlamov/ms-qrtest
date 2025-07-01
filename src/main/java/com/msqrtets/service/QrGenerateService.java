package com.msqrtets.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QrGenerateService {

    private static long counter = 0;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final String BASE_URL = "https://oxu.az/tag/";

    public String generateQRCode(String tagName) {
        String qrUrl = BASE_URL + tagName;
        String fileName = tagName + "_" + counter++ + ".png";

        try {
            BitMatrix matrix = new QRCodeWriter().encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
            Path filePath = FileSystems.getDefault().getPath(fileName);
            MatrixToImageWriter.writeToPath(matrix, "PNG", filePath);

            log.info("QR kod uğurla yaradıldı: {}", fileName);
            return fileName;
        } catch (WriterException | IOException e) {
            log.error("QR kod yaradılarkən xəta baş verdi", e);
            return null;
        }
    }
}
