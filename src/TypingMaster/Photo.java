/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingMaster;

import static com.sun.javafx.runtime.SystemProperties.getCodebase;
import static com.sun.webkit.graphics.WCImage.getImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static sun.applet.AppletResourceLoader.getImage;

/**
 *
 * @author sadu
 */
public class Photo extends JPanel {

    Image a;
    // home page image
    public Photo() {
        try {
            a = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("ss.jpg"), "ss.jpg"));
        } catch (Exception e) {
            /*handled in paintComponent()*/ }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(a, 0, 0, 1000, 670, null);

    }

}
