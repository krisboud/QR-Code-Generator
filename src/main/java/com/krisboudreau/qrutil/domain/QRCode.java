package com.krisboudreau.qrutil.domain;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Class representation of a Quick Response Code
 *
 * Created by krisboud(Kris Boudreau) on 2015-12-18.
 */
public class QRCode {

    private static final String IMG_FORMAT = "jpg";

    private String uniqueID;

    private String url;

    private int width;

    private int height;

    private Color foreColor;

    private Color backColor;

    public QRCode() {
        uniqueID = UUID.randomUUID().toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getForegroundColor() {
        return foreColor;
    }

    public void setForegroundColor(int red, int green, int blue) {
        this.foreColor = new Color(red, green, blue);
    }

    public Color getBackgroundColor() {
        return backColor;
    }

    public void setBackgroundColor(int red, int green, int blue) {
        this.backColor = new Color(red, green, blue);
    }

    public Boolean generateQRCodeImage() throws WriterException, IOException {

        File file = new File(uniqueID + "." + IMG_FORMAT);

        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrWriter.encode(getUrl(), BarcodeFormat.QR_CODE, getWidth(), getHeight());
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);


        // Start to Write the QRCode Image.
        System.out.println("Generating QR Code.");
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(getBackgroundColor());
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(getForegroundColor());

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        System.out.println("Writing QR Code to file. \n");

        boolean results = ImageIO.write(image, IMG_FORMAT, file);

        if (results) {
            System.out.println("QR Code successfully written. \n");
        } else {
            System.out.println("Error: Could not write QR Code. \n");
        }

        return results;
    }
}
