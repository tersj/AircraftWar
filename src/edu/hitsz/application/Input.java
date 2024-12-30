package edu.hitsz.application;

import edu.hitsz.printScore.PlayerScore;
import edu.hitsz.printScore.ScoreDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Input {
    private JPanel Button;
    private JButton yes;
    private JButton no;
    private JLabel print1;
    private JLabel yourScore;
    private JTextField yourName;
    private JLabel print2;
    private JPanel top;
    private JPanel middle;
    private JPanel mainPanel;

    private int flag=1;

    public Input(int score) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.mainPanel.getSize();
        JFrame frame = new JFrame("Input");
        frame.setContentPane(this.mainPanel);
        //设置窗口的大小和位置,居中放置
        frame.setLocation(((int) screenSize.getWidth() - (int) frameSize.getWidth()) / 2, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        yourScore.setText(String.valueOf(score));

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=1;
                String playerName=yourName.getText();
                try {
                    Print(score,playerName,flag);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                ScoreTable scoreTable=new ScoreTable();
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=0;
                String playerName=yourName.getText();
                try {
                    Print(score,playerName,flag);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                ScoreTable scoreTable=new ScoreTable();
            }
        });
    }

    public void Print(int score,String playerName,int flag) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String time = format.format(new Date());
        ScoreDaoImpl scoreDao=new ScoreDaoImpl();
        scoreDao.readFile();
        scoreDao.clearFile();
        if(flag==1){
            scoreDao.doAdd(new PlayerScore(playerName, score, time, Main.degree));
        }
        List<PlayerScore> playerScores=scoreDao.getAllScores();
        scoreDao.writeFile(playerScores);
        System.out.println("************************************************************");
        System.out.println("                    得分排行榜                    ");
        System.out.println("************************************************************");
        System.out.println("  排 名        名称         分数        达成时间        难度");
        int index = 1;
        for(PlayerScore playerScore : playerScores){
            System.out.printf(" 第 %d 名 ：%11s,%8d,%15s，%8s\n",index,playerScore.getPlayerName(),
                    playerScore.getPlayerScore(),playerScore.getTime(),playerScore.getDegree());
            index++;
        }
    }
}
