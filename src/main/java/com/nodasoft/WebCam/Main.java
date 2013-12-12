package com.nodasoft.WebCam;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Property.init();
        
        WebCam app = new WebCam(1024, 768);
        app.start();
    }
}