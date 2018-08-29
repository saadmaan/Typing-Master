/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingMaster;

import java.util.Random;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Buddha
 */
public class Game extends JPanel{

    public static final String[] Array = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "p", "Q",
        "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", ",", ";", ":", "<", ">", "?", "/", "'", "\\",
        "+", "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "[", "]", "{", "}","|" };

    String ch;
    int xpos, ypos;
    public static BufferedImage image;

   
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
//    }


    public Game() {
        try {
            image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("ii.png"), "ii.png"));
        } catch (Exception e) {
            /*handled in paintComponent()*/ }
       
        ypos = ypos1();
        xpos = 0;
        ch = Array[generateRandomNumber()];
    }
  int h=0;
    public int ypos1() {
        Random rand = new Random();
        
        int x = rand.nextInt(NewGame.HEIGHT - 60);
        
        //x += 10;
        if (x >= 500) {
            x -= 60;
        }
        else if (x <= 50) {
            x += 60;
        }
        if(h>x && (h-x)<60){
            x-= 100;
        }
        else if(h<x && (x-h)<60){
            x+= 100;
        }
        h=x;
        
         
        return (x);
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        int x = rand.nextInt(Array.length);
        return (x);
    }

    public static boolean isValid(String ch) {
        for (int i = 0; i < Array.length; ++i) {
            if (Array[i].equals(ch)) {
                return true;
            }
        }
        return false;
    }
}
