/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TypingMaster;

/**
 *
 * @author Buddha
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class NewGame extends JPanel {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 670;
    public static int life;
    public static int score;

    JFrame myGameFrame;
    public int minute = 0, second = 0;

    public int updateTime = 0;
    public static String totalTime = "";
    public static final int CREATION_TIME[]
            = {
                1800,
                1500,
                1100,
                950,
                780
            };
    public static final int FALL_TIME[] = {
        9,
        7,
        5,
        4,
        3
    };

    ArrayList<Game> display = new ArrayList<>();

    Timer CreationTimer, FallTimer, ClockTimer;

    Frame myParent;
    int myLevel;

    String pressedChar = "";
    boolean gameRunning = true;

    public NewGame(Frame parent, int level) {
        
        myLevel = level;
        myParent = parent;
        myGameFrame = new JFrame("Typing Master");
        myGameFrame.setLocationRelativeTo(null);
        pic p = new pic();
        p.setLayout(null);
        myGameFrame.add(p);
        initTimers();
    }

    public void initTimers() {

     
        CreationTimer = new Timer(CREATION_TIME[myLevel], new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addObject();
            }
        });
        FallTimer = new Timer(FALL_TIME[myLevel], new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeObject();
            }
        });
        ClockTimer = new Timer(1000 , new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTimer();
            }
        });
    }

    public void initGame() {

        myParent.setVisible(false);
        this.setBackground(Color.LIGHT_GRAY);
        //gameRunning = true;
        myGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myGameFrame.add(this);
        myGameFrame.setSize(WIDTH, HEIGHT);
        myGameFrame.setVisible(true);
        myGameFrame.setResizable(false);
        myGameFrame.setLayout(null);
        myGameFrame.setLocationRelativeTo(null);
     
        //myGameFrame.setBackground(Color.black);

        //addObject();
        CreationTimer.start();
        ClockTimer.start();
        FallTimer.start();

        myGameFrame.addKeyListener(new KeyReleaseListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                //System.out.println(e.getKeyChar()); 
                checkChar("" + e.getKeyChar());
            }
        });

        myGameFrame.addWindowListener(new WindowCloseListener() {
            public void windowClosing(WindowEvent e) {
                stopGame();
                myParent.dispose();
            }
        });
    }

    public void gameTimer() {
        // calculate span totalTime
        second++;
        if (second == 60) {
            second = 0;
            minute++;
            updateTime++;
        }
        String m = "", s = "";
        if (second < 10) {
            s = ("0" + second);
        } else {
            s = ("" + second);
        }
        if (minute < 10) {
            m = "0" + minute;
        } else {
            m = ("" + minute);
        }
        totalTime = "Time : " + m + " : " + s;

        // updateTime level
        if (updateTime == 1 && (myLevel + 1) != 5) {
            stopGame();
            updateTime = 0;
            minute = 0;
            second = 0;
            myLevel++;       
            display.clear();
            pressedChar = "";
            gameRunning = true;
            initTimers();
            //addObject();
            startGame();

        }
    }

    public void addObject() {
        display.add(new Game());
        pressedChar = "";
    }

    public void checkChar(String str) {

        pressedChar = str;

        if (pressedChar.length() > 0) {
            int es = 27;
            String esc = "" + (char) es;

            if (pressedChar.equals("\n")) {
                gameRunning = true;
                startGame();
            }else if (pressedChar.equals(" ")) {
                gameRunning = false;
                stopGame();
            }else if (pressedChar.equals(esc)) {
                stopGame();
                showMain();
            }
        }

        if (gameRunning) {
            boolean correctKey = false;
            Iterator<Game> it = display.iterator();
            while (it.hasNext()) {
                Game game = it.next();
                if (game.ch.equals(str)) {
                    it.remove();
                    //  increase score
                    score += (myLevel + 1);
                    correctKey = true;
                    playSound("button-17.wav");
                    break;
                }
            }
            if (!correctKey && Game.isValid(pressedChar)) {
                playSound("button-14.wav");
            }
        }

        //repaint();
    }

    public void updateTimeObject() {

        // updateTime all objects
        Iterator<Game> it = display.iterator();
        while (it.hasNext()) {
            Game game = it.next();
            game.xpos++;
            if (game.xpos > NewGame.WIDTH) {
                it.remove();
                //  decrease life
                life--;
                playSound("finish.wav");
            }
        }

        // life ended. game over
        if (life <= 0) {
            stopGame();
            showScore();
        }

        repaint();
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
   
    public void  startGame() {
        CreationTimer.start();
        ClockTimer.start();
        FallTimer.start();
    }

    public void stopGame() {
        CreationTimer.stop();
        FallTimer.stop();
        ClockTimer.stop();
    }

    public void showScore() {
        myGameFrame.dispose();
        FinishGame fw = new FinishGame();
        fw.Main(myParent, score);
    }

    public void showMain() {
        myGameFrame.dispose();
        myParent.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameRunning) {
            // drawing characters
            Iterator<Game> it = display.iterator();
            while (it.hasNext()) {
                Game game = it.next();

               
                g.drawImage(game.image,game.xpos-30, game.ypos-35, 480/3, 150/3, null);
                 Font font = new Font("Arial", Font.ROMAN_BASELINE + Font.BOLD, 20);//Segoe Print
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString(game.ch, game.xpos, game.ypos);
            }
        }

        // draw score
        Font font = new Font("Segoe UI", Font.BOLD + Font.PLAIN, 30);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("SCORE: " + String.valueOf(score), 10, 50);
        g.setColor(Color.BLUE);
        g.drawString("LEVEL: " + String.valueOf(myLevel + 1), 10, 120);

        g.setColor(Color.RED);
        g.drawString("LIFE: " + String.valueOf(life), 10, 85);

        g.setColor(Color.BLACK);
        g.drawString(totalTime, 780, 50);

        if (gameRunning) {
            // show pressed character 
            if (Game.isValid(pressedChar)) {
                font = new Font("Segoe Print", Font.BOLD + Font.PLAIN, 60);
                g.setFont(font);
                g.setColor(Color.RED);
                g.drawString(pressedChar, WIDTH / 2, HEIGHT / 2);
            }
        } else {
            // draw paused text
            font = new Font("Segoe Print", Font.BOLD + Font.PLAIN, 80);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("Paused", 370, 280);
            font = new Font("Segoe Print", Font.BOLD + Font.PLAIN, 40);
            g.setFont(font);
            g.setColor(Color.BLUE);
            g.drawString("Pressed Enter to Continue", 280, 380);
        }
    }

}
