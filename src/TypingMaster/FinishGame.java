/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
// * and open the template in the editor.
 */
package TypingMaster;

import static TypingMaster.StartMenu.INITIAL_LIFE;
import static TypingMaster.StartMenu.INITIAL_SCORE;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Buddha
 */
public class FinishGame {

    /**
     * @param args the command line arguments
     */
    public static int yourScore, highestScore;

    public void Main(Frame myParent, int scores) {

        int score = scores;
        int current_score = score;
        int previous_score = 0, tem;
        // add score in file and compare it with the previous score
        try {
            FileReader fr = new FileReader("scorefile.txt");
            try (BufferedReader pr = new BufferedReader(fr)) {
                String str;
                while ((str = pr.readLine()) != null) {
                    previous_score = Integer.parseInt(str);
                }
                pr.close();
            }
        } catch (Exception e) {
            System.out.println("error");
        }

        if (current_score >= previous_score) {
            tem = current_score;
            try {
                FileWriter fw = new FileWriter("scorefile.txt");
                try (BufferedWriter pw = new BufferedWriter(fw)) {

                    pw.write(Integer.toString(score));
                    pw.close();
                }
            } catch (Exception e) {
                System.out.println("error");
            }

        } else {
            tem = previous_score;
        }
        yourScore = score;
        highestScore = tem;
        Frame my = myParent;
        myParent.setVisible(false);
        JFrame finishGameframe = new JFrame("The End");

        
        Cover overPanel = new Cover();
        overPanel.setLayout(null);
        JButton playbtn = new JButton("Play Again");
        JButton backbtn = new JButton("Back");
        playbtn.setBounds(400, 420, 240, 40);
        playbtn.setBackground(Color.ORANGE);
        Cursor c2 = new Cursor(Cursor.HAND_CURSOR);
        playbtn.setCursor(c2);
        backbtn.setBounds(400, 490, 240, 40);
        backbtn.setBackground(Color.ORANGE);
        Cursor c3 = new Cursor(Cursor.HAND_CURSOR);
        backbtn.setCursor(c3);
        playbtn.setFont(new Font("Arial", Font.PLAIN, 30));
        backbtn.setFont(new Font("Arial", Font.PLAIN, 30));

        playbtn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                
                playSound("button-7.wav");
                finishGameframe.dispose();
                NewGame pw = new NewGame(myParent, StartMenu.level);
                NewGame.score = INITIAL_SCORE;
                NewGame.life = INITIAL_LIFE;
                pw.initGame();
            }
        });

        backbtn.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                playSound("button-7.wav");
                finishGameframe.dispose();
                myParent.setVisible(true);
            }
        });

        finishGameframe.add(playbtn);

        finishGameframe.add(backbtn);
        overPanel.setBackground(Color.GREEN);
        finishGameframe.add(overPanel);
        finishGameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        finishGameframe.setSize(1000, 670);
        finishGameframe.setVisible(true);
        finishGameframe.setResizable(false);

    }

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
