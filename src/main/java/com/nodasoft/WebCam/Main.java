package com.nodasoft.WebCam;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		Webcam webcam = Webcam.getDefault();
		if(webcam != null){
			webcam.open();
			ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
			webcam.close();
		} else {
			System.out.println("Web cam is not found!");
		}
	}
}
