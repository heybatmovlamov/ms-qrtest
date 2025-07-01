package com.msqrtets.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.springframework.stereotype.Service;

@Service
public class QrGenerateService {

    public String generateQRCode(String qrText, String fileName) {
        int width = 300;
        int height = 300;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height);
            Path path = FileSystems.getDefault().getPath(fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR kod yaradıldı: " + fileName);
            return "QR kod yaradıldı: " + fileName;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return "QR kod yaradılarkən xəta baş verdi: " + e.getMessage();
        }
    }
}
