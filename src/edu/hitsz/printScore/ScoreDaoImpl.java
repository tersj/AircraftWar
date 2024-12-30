package edu.hitsz.printScore;

import edu.hitsz.application.Game;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScoreDaoImpl implements PlayerScoreDao{

    private List<PlayerScore> playerScores;
    File file = new File("scoreFile.txt");

    @Override
    public void doAdd (PlayerScore playerScore){
        playerScores.add(playerScore);
    }

    @Override
    public void doDelete(List<PlayerScore> playerScores,String time){
        for (PlayerScore playerScore:playerScores){
            if(playerScore.getTime().equals(time)){
                playerScores.remove(playerScore);
                return;
            }
        }
    }

    public void readFile(){
        playerScores=new ArrayList<PlayerScore>();
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            String strin;
            while ((strin = br.readLine()) != null) {
                String[] strinSave = strin.split("\\|");
                PlayerScore playerScore = new PlayerScore(strinSave[0], Integer.parseInt(strinSave[1]), strinSave[2], strinSave[3]);
                playerScores.add(playerScore);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile(){
        try {
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(List<PlayerScore> playerScores){
        try {
            FileWriter fop =new FileWriter(file,true);
            for (PlayerScore playerScore:playerScores){
                String strout= playerScore.getPlayerName()+"|"+Integer.toString(playerScore.getPlayerScore())+"|"+
                        playerScore.getTime()+"|"+playerScore.getDegree();
                fop.write(strout+"\r\n");
            }
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayerScore> getAllScores(){
        Collections.sort(playerScores, new Comparator<PlayerScore>() {
            @Override
            public int compare(PlayerScore o1, PlayerScore o2) {
                return o2.getPlayerScore()-o1.getPlayerScore();
            }
        });
        return this.playerScores;
    }


}
