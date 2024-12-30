package edu.hitsz.application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JButton easy;
    private JButton hard;
    private JComboBox musicChoose;
    private JLabel music;
    private JPanel musicButton;
    private JPanel degreeButton;
    private JPanel mainPanel;
    private JButton normal;

    public static String gameDegree;

    public Start() {
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                synchronized (Main.MAIN_LOCK){
                    try{
                        gameDegree="easy";
                        Main.MAIN_LOCK.notify();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                synchronized (Main.MAIN_LOCK){
                    try{
                        gameDegree="normal";
                        Main.MAIN_LOCK.notify();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                synchronized (Main.MAIN_LOCK){
                    try{
                        gameDegree="hard";
                        Main.MAIN_LOCK.notify();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        musicChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.MAIN_LOCK){
                    try{
                        if(musicChoose.getSelectedItem().equals("open")){
                            MusicThread.open=true;
                        }
                        else{
                            MusicThread.open=false;
                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }

            }
        });
    }

    public JPanel getStartPanel() {
        return mainPanel;
    }
}
