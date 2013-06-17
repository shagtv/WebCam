package com.nodasoft.WebCam;

import com.github.sarxos.webcam.Webcam;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author admin
 */
public class WebCam extends JFrame {

    Image img;
    Webcam webcam;

    public WebCam() throws IOException {
        super("Web Cam 1.0");

        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(1024, 768));
        webcam.open();
        img = webcam.getImage();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (img != null) {
            g.drawImage(img, 0, 0, getSize().width, getSize().height, this);

        } else {
            g.clearRect(0, 0, getSize().width, getSize().height);
        }
    }

    public void start() {
        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);

        JMenu newMenu = new JMenu("New");
        newMenu.setFont(font);
        fileMenu.add(newMenu);

        JMenuItem txtFileItem = new JMenuItem("Text file");
        txtFileItem.setFont(font);
        newMenu.add(txtFileItem);

        JMenuItem imgFileItem = new JMenuItem("Image file");
        imgFileItem.setFont(font);
        newMenu.add(imgFileItem);

        JMenuItem folderItem = new JMenuItem("Folder");
        folderItem.setFont(font);
        newMenu.add(folderItem);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);

        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.setFont(font);
        fileMenu.add(closeItem);

        JMenuItem closeAllItem = new JMenuItem("Close all");
        closeAllItem.setFont(font);
        fileMenu.add(closeAllItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);

        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        String height = Property.get("cam.height");
        String width = Property.get("cam.width");
        String fps = Property.get("fps");
        if (height == null || width == null || fps == null) {
            System.err.println("Error in params");
            System.exit(0);
        }

        setPreferredSize(new Dimension(Integer.parseInt(height), Integer.parseInt(width)));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                img = webcam.getImage();
                repaint();
            }
        };
        int delay = (int) 1000 / Integer.parseInt(fps);
        new Timer(delay, taskPerformer).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                webcam.close();
                dispose();
                System.exit(0);
            }
        });
    }
}