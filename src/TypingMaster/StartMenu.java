/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingMaster;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Buddha
 */
public class StartMenu {

    public static final int INITIAL_LIFE = 5 ;
    public static final int INITIAL_SCORE = 0;
    public static int level = 0;

    public static void main(String[] args) throws IOException {
        new StartMenu();
    }

    public JFrame mainFrame;

    public StartMenu() {

       mainFrame = new JFrame("Typing Master");
       
        Photo photoPanel = new Photo();
        photoPanel.setLayout(null);

        JButton newGame = new JButton("new game");
        JComboBox option = new JComboBox();
        JButton exit = new JButton("EXIT");
        newGame.setBounds(150, 30, 130, 35);
        newGame.setBackground(Color.LIGHT_GRAY);
        option.setBounds(400, 30, 130, 35);
        option.setBackground(Color.YELLOW);
        exit.setBounds(650, 30, 130, 35);
        exit.setBackground(Color.red);
        Cursor c1 = new Cursor(Cursor.HAND_CURSOR);
        newGame.setCursor(c1);
        newGame.setFont(new Font("Arial", Font.ITALIC, 20));
        Cursor c2 = new Cursor(Cursor.HAND_CURSOR);
        option.setCursor(c2);
        option.setFont(new Font("Arial", Font.ITALIC, 20));
        Cursor c3 = new Cursor(Cursor.HAND_CURSOR);
        exit.setCursor(c3);
        exit.setFont(new Font("Arial", Font.ITALIC, 20));

        String str[] = {"Easy", "Medium", "Hard"};
        for (int i = 0; i < 3; i++) {
            option.addItem(str[i]);
        }
        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (option.getSelectedItem() == "Easy") {
                    level = 0;
                } else if (option.getSelectedItem() == "Medium") {
                    level = 1;
                } else if (option.getSelectedItem() == "Hard") {
                    level = 2;
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              playSound("button-7.wav");
             mainFrame.dispose();
            }
        });

        newGame.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
               playSound("button-7.wav");
                NewGame pw = new NewGame( mainFrame, level);
                NewGame.score = INITIAL_SCORE;
                NewGame.life = INITIAL_LIFE;
                pw.initGame();
            }
        });

        photoPanel.add(newGame);
        photoPanel.add(option);
        photoPanel.add(exit);

       mainFrame.add(photoPanel);
       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       mainFrame.setLocationRelativeTo(null);

       mainFrame.setSize(1000 , 670);
       mainFrame.setVisible(true);
       mainFrame.setResizable(false);
       mainFrame.setLocationRelativeTo(null);
    }

//    public void aboutClicked() {
//      
//        playSound("button-7.wav");
//        Aboutinfo ab = new Aboutinfo();
//        ab.About(mainFrame);
//    }
    // play sound 
       public void playSound(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(fileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("ERROR in StartMenu!!!");
        }
    }

  

  
}
