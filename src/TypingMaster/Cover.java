/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingMaster;

import static TypingMaster.FinishGame.highestScore;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Buddha
 */
public class Cover extends JPanel {

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        //Graphics2D g2 = (Graphics2D) g;

        setLayout(null);
       
        Label sco = new Label("Your score: "+FinishGame.yourScore);
        sco.setFont(new Font("Arial", Font.PLAIN, 50));
        sco.setForeground(Color.WHITE);
        sco.setBounds(320, 220, 500, 120);
        add(sco);
        
        Label hsco = new Label("Highest score: "+FinishGame.highestScore);
        hsco.setFont(new Font("Arial", Font.PLAIN, 45 ));
        hsco.setForeground(Color.white);
        hsco.setBounds(320, 300,500, 120);
        add(hsco);
        
        Label ox = new Label("GAME OVER");
        ox.setFont(new Font("Serif", Font.PLAIN+Font.BOLD, 80));
        ox.setForeground(Color.CYAN);
        ox.setBounds(300, 40,500, 300 );
        add(ox);
    }

}
