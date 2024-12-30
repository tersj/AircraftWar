package edu.hitsz.application;

import edu.hitsz.printScore.PlayerScore;
import edu.hitsz.printScore.ScoreDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScoreTable extends Thread{
    JPanel mainPanel;
    private JPanel scorePanel;
    private JPanel buttonPanel;
    private JTable scoreTable;
    private JScrollPane TableScrollPane;
    public  JButton deleteButton;
    private JLabel degee1;
    private JLabel degree2;
    private JLabel rankList;
    private JPanel degree;

    public static int flag=0;
    public ScoreTable(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.mainPanel.getSize();
        JFrame frame = new JFrame("ScoreTable");
        frame.setContentPane(this.mainPanel);
        //设置窗口的大小和位置,居中放置
        frame.setLocation(((int) screenSize.getWidth() - (int) frameSize.getWidth()) / 2, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        String [] columnName={"排名","玩家名","得分","记录时间","难度"};

        ScoreDaoImpl scoreDao=new ScoreDaoImpl();
        scoreDao.readFile();
        List<PlayerScore> playerScores=scoreDao.getAllScores();

        String [][] tableData = new String[playerScores.size()][5];
        int i=0;
        for(PlayerScore playerScore:playerScores){
            tableData[i][0]= String.valueOf(i+1);
            tableData[i][1]= playerScore.getPlayerName();
            tableData[i][2]= String.valueOf(playerScore.getPlayerScore());
            tableData[i][3]= playerScore.getTime();
            tableData[i][4]= playerScore.getDegree();
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(tableData,columnName){};

        scoreTable.setModel(model);
        TableScrollPane.setViewportView(scoreTable);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=scoreTable.getSelectedRow();
                int input = JOptionPane.showConfirmDialog(null,"是否删除选中数据：","Delete",
                        JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                if(row!=-1 && input==0){
                    scoreDao.doDelete(playerScores,tableData[row][3]);
                    scoreDao.clearFile();
                    scoreDao.writeFile(playerScores);
                    model.removeRow(row);
                }
            }
        });

    }

    public void setFlag(int flag){
        ScoreTable.flag=flag;
    }
}
