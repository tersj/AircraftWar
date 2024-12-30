package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 程序入口
 * @author hitsz
 */
public class Main{

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object MAIN_LOCK = new Object();

    public static String degree;

    public static void main(String[] args) throws IOException {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Start start=new Start();
        JPanel StartPanel=start.getStartPanel();
        frame.add(StartPanel);
        frame.setVisible(true);
        synchronized (MAIN_LOCK){
            try{
                MAIN_LOCK.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        frame.remove(StartPanel);
        frame.setVisible(false);
        Game game;
        switch (Start.gameDegree){
            case "easy":
                game=new EasyGame();
                degree="easy";
                break;
            case "normal":
                game=new NormalGame();
                degree="normal";
                break;
            case "hard":
                game=new HardGame();
                degree="hard";
                break;
            default:
                game=null;
        }

        frame.add(game);

        frame.setVisible(true);
        game.action();

        synchronized (MAIN_LOCK){
            try{
                new MusicThread("src/videos/bgm.wav").start();
                MAIN_LOCK.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        MusicThread.bossBgm=false;
        MusicThread.bgBgm=false;
        frame.remove(game);
        frame.dispose();
    }

}
